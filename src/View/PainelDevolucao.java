package View;

import Controller.DevolucaoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PainelDevolucao extends JPanel {

    private JPanel informationPanel;
    private JTextField txtIdItem;
    private JLabel idDevolucao;
    private JLabel usuario;
    private JLabel itemEmprestado;

    private JTextArea areaDetalhes;
    private DevolucaoController controller;
    private JButton btnConfirmar, btnConfirmarDevolucao;

    public PainelDevolucao() {

        informationPanel = new JPanel();
        this.setLayout(new FlowLayout());

        idDevolucao = new JLabel("Id do Item:");

        txtIdItem = new JTextField(15);
        btnConfirmar = new JButton("Confirmar");
        btnConfirmarDevolucao = new JButton("Confirmar devolução");
        this.add(idDevolucao);
        this.add(txtIdItem);
        this.add(btnConfirmar);

        this.setVisible(true);


    }



    public void addBtnConfirmarListener(ActionListener listener) {
        this.btnConfirmar.addActionListener(listener);
    }

    public String gettxtIdItem(){
        return txtIdItem.getText();
    }

    public void limparcampoID(){
        txtIdItem.setText("");
    }



    public void msg(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem);
    }


}
