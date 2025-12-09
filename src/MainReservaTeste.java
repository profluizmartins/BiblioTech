
import controller.ReservaController;
import model.*;
import view.PainelReserva;

import javax.swing.*;

public class MainReservaTeste {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            // 1. Instanciar Repositórios (Simulação do Banco de Dados em Memória)
            UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();
            AcervoRepositorio acervoRepo = new AcervoRepositorio();
            ReservaRepositorio reservaRepo = new ReservaRepositorio();
            EmprestimoRepositorio emprestimoRepo = new EmprestimoRepositorio();

            // 2. Popular com Massa de Dados Falsa (Para testes manuais)
            System.out.println("Gerando massa de dados...");

            // Usuários
            Usuario u1 = new Usuario(1, "João Silva", "1001");
            Usuario u2 = new Usuario(2, "Maria Souza", "1002");
            Usuario u3 = new Usuario(3, "Pedro Suspenso", "9999");
            u3.setStatus(Usuario.SUSPENSO_MULTA); // Testar bloqueio de suspenso

            usuarioRepo.salvar(u1);
            usuarioRepo.salvar(u2);
            usuarioRepo.salvar(u3);

            // Itens
            Livro l1 = new Livro(101, "Java Completo", "Deitel");
            l1.setStatus(ItemAcervo.EMPRESTADO); // Pode ser reservado

            Livro l2 = new Livro(102, "Clean Code", "Martin");
            l2.setStatus(ItemAcervo.DISPONIVEL); // NÃO pode ser reservado (erro esperado)

            Livro l3 = new Livro(103, "Harry Potter", "Rowling");
            l3.setStatus(ItemAcervo.EMPRESTADO); // Pode ser reservado

            acervoRepo.salvar(l1);
            acervoRepo.salvar(l2);
            acervoRepo.salvar(l3);

            // Empréstimos (Simulando que o Usuário 1 está com o Livro 3)
            // Isso serve para testar se o Usuário 1 tenta reservar o Livro 3 (Regra: não pode reservar o que já está com ele)
            emprestimoRepo.registrarEmprestimo(u1, l3);

            // 3. Injeção de Dependência no Controller
            ReservaController controller = new ReservaController(
                    reservaRepo, usuarioRepo, acervoRepo, emprestimoRepo
            );

            // 4. Configuração da View
            PainelReserva painel = new PainelReserva();
            painel.setController(controller);

            // 5. Exibição da Janela
            JFrame frame = new JFrame("BiblioTech - Módulo de Reservas (Teste Integração)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(painel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Instruções no Console
            System.out.println("--- Sistema Iniciado ---");
            System.out.println("Dados para Teste:");
            System.out.println("Usuario Ativo: Matrícula '1001'");
            System.out.println("Usuario Ativo: Matrícula '1002'");
            System.out.println("Usuario Suspenso: Matrícula '9999'");
            System.out.println("Item Emprestado (Reservável): ID 101");
            System.out.println("Item Disponível (Erro ao Reservar): ID 102");
            System.out.println("Item Comigo (Erro ao Reservar): ID 103 (para User 1001)");
        });
    }
}