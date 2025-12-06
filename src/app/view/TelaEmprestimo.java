package app.view;

import app.controller.EmprestimoController;
import app.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Tela principal do módulo de Empréstimos.
 * Esta janela é aberta quando o usuário seleciona
 * o módulo de empréstimos no sistema geral.
 *
 * Diferente da main geral, esta tela funciona como um
 * container que carrega o PainelNovoEmprestimo.
 *
 * Grupo 4 - Empréstimos
 */
public class TelaEmprestimo extends JFrame {

    private PainelNovoEmprestimo painelNovoEmprestimo;

    public TelaEmprestimo() {

        setTitle("Módulo - Empréstimos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Repositórios e Config (dependências)
        UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();
        AcervoRepositorio acervoRepo = new AcervoRepositorio();
        EmprestimoRepositorio emprestimoRepo = new EmprestimoRepositorio();
        Configuracoes config = new Configuracoes();

        // --- Controller
        EmprestimoController controller = new EmprestimoController(
                usuarioRepo,
                acervoRepo,
                emprestimoRepo,
                config
        );

        // --- Painel principal do módulo
        painelNovoEmprestimo = new PainelNovoEmprestimo(controller);

        setLayout(new BorderLayout());
        add(painelNovoEmprestimo, BorderLayout.CENTER);
    }

    /**
     * Main SOMENTE para teste do Grupo 4.
     * No sistema final, o grupo da main geral chama esta janela.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new TelaEmprestimo().setVisible(true);
        });
    }
}