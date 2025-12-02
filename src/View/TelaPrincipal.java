package View;

import javax.swing.*;
import java.awt.*;

/**
 * Tela principal do módulo de Gestão de Usuários.
 * Esta classe representa a janela inicial que é exibida quando
 * o sistema é executado, carregando diretamente o módulo de
 * Gestão de Usuários, como especificado para este grupo.
 *
 * Diferente da versão geral do sistema, esta implementação
 * **não possui tela de boas-vindas**, **não possui troca de módulos**
 * e **abre exclusivamente o módulo de Gestão de Usuários**.
 *
 * Ela serve como container principal do painel PainelGestaoUsuarios.
 *
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Hnerique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class TelaPrincipal extends JFrame {

    private PainelGestaoUsuarios painelGestao;

    /**
     * Construtor responsável por configurar a janela e
     * inicializar o painel de gestão de usuários.
     */
    public TelaPrincipal() {

        setTitle("Módulo - Gestão de Usuários");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        painelGestao = new PainelGestaoUsuarios();

        setLayout(new BorderLayout());
        add(painelGestao.getPanel(), BorderLayout.CENTER);
    }

    /**
     * Método principal para execução da aplicação.
     * Configura o LookAndFeel e abre a janela principal.
     *
     * @param args Argumentos do sistema.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new TelaPrincipal().setVisible(true);
        });
    }
}
