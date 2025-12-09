package Main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.time.LocalDate;

import Model.Usuario;
import Model.UsuarioRepositorio;
import app.model.Emprestimo;
import app.model.ItemAcervo;
import app.model.StatusEmprestimo;
import Exceptions.CampoObrigatorioException;
import Exceptions.ValidacaoException;

import controller.MultaController;
import model.MultaRepositorio;
import view.PainelGestaoMultas;

/**
 * Classe principal responsável pela inicialização e execução do Módulo de Gestão de Multas.
 * <p>
 * Esta classe atua como o ponto de entrada da aplicação, realizando a configuração
 * das dependências (injeção de repositórios no controller), a geração de dados fictícios
 * (Mock) para fins de teste e a inicialização da interface gráfica (View).
 * </p>
 * * @author Grupo 7
 * @version 1.0
 */
public class Main {

    /**
     * Ponto de entrada (Entry Point) da aplicação Java.
     * <p>
     * Este método utiliza {@link SwingUtilities#invokeLater(Runnable)} para garantir que
     * a criação e atualização da interface gráfica ocorram na <i>Event Dispatch Thread</i> (EDT),
     * prevenindo problemas de concorrência no Swing.
     * </p>
     * * @param args Argumentos de linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                inicializarSistema();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro fatal: " + e.getMessage());
            }
        });
    }

    /**
     * Realiza a configuração inicial de todos os componentes do sistema.
     * <p>
     * O fluxo de execução deste método consiste em:
     * <ol>
     * <li>Instanciar os repositórios de dados (Persistência em memória).</li>
     * <li>Instanciar o Controller injetando as dependências necessárias.</li>
     * <li>Criar objetos de modelo (Usuário, ItemAcervo, Empréstimo) para simulação.</li>
     * <li>Gerar uma multa de teste baseada em um empréstimo atrasado.</li>
     * <li>Configurar e exibir a janela principal (JFrame) com o painel de gestão.</li>
     * </ol>
     * </p>
     * * @throws CampoObrigatorioException Se algum campo obrigatório do {@link ItemAcervo} não for preenchido corretamente.
     * @throws ValidacaoException Se houver violação de regras de validação ao criar o item do acervo.
     */
    private static void inicializarSistema() throws CampoObrigatorioException, ValidacaoException {
        
        MultaRepositorio multaRepo = new MultaRepositorio();
        UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();

        MultaController multaController = new MultaController(multaRepo, usuarioRepo);

        System.out.println("Inicializando dados de teste...");

        Usuario usuario = new Usuario(
            "Ana Souza", 
            "123.456.789-00", 
            "Rua das Flores, 10", 
            LocalDate.of(1995, 5, 20)
        );
        
        usuarioRepo.salvar(usuario);
        System.out.println("Usuário criado: " + usuario.getNome() + " (Matrícula: " + usuario.getMatricula() + ")");

        ItemAcervo livroTeste = new ItemAcervo(1, "Engenharia de Software Moderna", 2020, "EMPRESTADO") {

        };

        LocalDate dataEmprestimo = LocalDate.now().minusDays(15); 
        LocalDate dataPrevista = LocalDate.now().minusDays(5);    

        Emprestimo emprestimoAtrasado = new Emprestimo(
            501, 
            usuario, 
            livroTeste, 
            dataEmprestimo, 
            dataPrevista, 
            StatusEmprestimo.ATIVO
        );
        
        multaController.gerarMultaPorAtraso(emprestimoAtrasado, 5, 3.50);
        
        System.out.println("Multa gerada com sucesso para o usuário " + usuario.getNome());
        
        JFrame frame = new JFrame("Sistema Bibliotecário - Módulo de Multas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PainelGestaoMultas painel = new PainelGestaoMultas(multaController);
        
        frame.getContentPane().add(painel);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}