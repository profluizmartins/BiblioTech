import javax.swing.*;
import View.PainelGestaoUsuarios;

/**
 * Classe principal responsável por iniciar o módulo de Gestão de Usuários.
 * Configura o Look and Feel do sistema e inicializa a interface gráfica
 * contendo exclusivamente o painel de gerenciamento de usuários.
 *
 * Esta classe executa o módulo de forma independente, pois a TelaPrincipal
 * não é utilizada neste contexto.
 * 
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Hnerique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class MainApp {

    /**
     * Método principal da aplicação. Configura a aparência da interface gráfica
     * e exibe a janela contendo o painel de gestão de usuários.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestão de Usuários - BiblioTech");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);

            PainelGestaoUsuarios painel = new PainelGestaoUsuarios();
            frame.setContentPane(painel.getPanel());

            frame.setVisible(true);
        });
    }
}
