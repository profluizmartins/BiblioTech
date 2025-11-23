package View;

import Controller.DevolucaoController;
import Model.Emprestimo;
import Model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelDevolucao extends JFrame {
    private JFrame frame;
    private JPanel principalPanel;
    private JPanel informationPanel;
    private JTextArea areaDetalhes;
    private DevolucaoController controller;

    public PainelDevolucao() {
        frame = new JFrame();
        principalPanel = new JPanel();
        informationPanel = new JPanel();
        principalPanel.setLayout(new FlowLayout());

        JLabel idDevolucao = new JLabel("Id do Item:");
        idDevolucao.setText("Id do Item:" );
        JTextField txtIdItem = new JTextField(15);
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnConfirmarDevolucao = new JButton("Confirmar devolução");
        principalPanel.add(idDevolucao);
        principalPanel.add(txtIdItem);
        principalPanel.add(btnConfirmar);
        frame.setTitle("Sistema de Devolução");
        frame.add(principalPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtIdItem.getText());
                    controller.realizarDevolucao(id);
                    txtIdItem.setText(""); // Limpa campo
                    //frame.setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido.");
                }
            }
        });
    }
}
