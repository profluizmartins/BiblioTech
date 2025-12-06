package test;

import javax.swing.JFrame;

import Model2.UsuarioRepositorio;
import appmodel.Emprestimo;
import controller.MultaController;
import model.MultaRepositorio;
import view.PainelGestaoMultas;

import java.time.LocalDate;

import Model2.Usuario;

public class TestePainel {

    public static void main(String[] args) {

        // Repositórios e controller
        UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();
        MultaRepositorio multaRepo = new MultaRepositorio();
        MultaController controller = new MultaController(multaRepo, usuarioRepo);

        // Criar um usuário
        Usuario u = new Usuario(
                "Maria Oliveira",
                "98765432100",
                "Avenida Central",
                LocalDate.of(1998, 2, 14)
        );
        usuarioRepo.salvar(u);

        // Criar multiplas multas para testar a tabela
        Emprestimo e1 = new Emprestimo(1, u, null,
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(10),
                null);

        Emprestimo e2 = new Emprestimo(2, u, null,
                LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(5),
                null);

        controller.gerarMultaPorAtraso(e1, 10, 2.0);
        controller.gerarMultaPorAtraso(e2, 5, 2.0);

        // Janela de teste
        JFrame frame = new JFrame("Teste da Tela de Multas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        PainelGestaoMultas painel = new PainelGestaoMultas(controller);
        frame.add(painel);

        frame.setVisible(true);
    }
}