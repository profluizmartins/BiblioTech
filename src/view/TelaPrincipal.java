package view;
//w
import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private JLabel lblStatus;
    private JTabbedPane areaModulos; //abas para carregar os módulos

    public TelaPrincipal() {
        super("BiblioTech - Sistema de Gerenciamento");
        configurarTela();
    }

    private void configurarTela() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //menu superior
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemSair);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);

        //area central
        areaModulos = new JTabbedPane();
        add(areaModulos, BorderLayout.CENTER);

        //status
        lblStatus = new JLabel("Usuário não logado");
        lblStatus.setBorder(BorderFactory.createEtchedBorder());
        add(lblStatus, BorderLayout.SOUTH);
    }

    public void atualizarStatus(String nome, String cargo) {
        lblStatus.setText("Bem-vindo(a), " + nome + " | Cargo: " + cargo);
    }

    public void adicionarModulo(String titulo, JPanel painel) {
        areaModulos.addTab(titulo, painel);
    }
}