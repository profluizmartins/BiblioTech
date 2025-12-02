import javax.swing.*;
import View.PainelGestaoUsuarios;

public class MainApp {

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
