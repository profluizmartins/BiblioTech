package View;

import Controller.DevolucaoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelDevolucao extends JPanel {
// extends JPanel ou JFrame??
    private JTextField txtIdItem;
    private JLabel IdDevolucao;
    private JTextArea areaDetalhes;
    private JButton btnConfirmar;
    private DevolucaoController controller;

    public PainelDevolucao(DevolucaoController controller) {
        IdDevolucao = new JLabel("Id do Item:");
        IdDevolucao.setText("Id do Item:" );
        txtIdItem = new JTextField(15);
        btnConfirmar = new JButton("Confirmar");
        this.add(IdDevolucao);
        this.add(txtIdItem);
        this.add(btnConfirmar);
     }



}
