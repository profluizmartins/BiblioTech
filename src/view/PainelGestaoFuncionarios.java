package view;

import javax.swing.*;
import java.awt.*;
import controller.FuncionarioController;
import exception.ValidacaoException;

public class PainelGestaoFuncionarios extends JPanel {
    private JTextField txtNome, txtCpf, txtLogin;
    private JPasswordField txtSenha;
    private JComboBox<String> cbCargo;
    private JButton btnSalvar;
    private FuncionarioController controller;

    public PainelGestaoFuncionarios() {
        this.controller = new FuncionarioController(); 
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridLayout(6, 2, 8, 8)); 

        add(new JLabel("Nome Completo:")); 
        add(txtNome = new JTextField());

        add(new JLabel("CPF:")); 
        add(txtCpf = new JTextField());

        add(new JLabel("Login de Acesso:")); 
        add(txtLogin = new JTextField());

        add(new JLabel("Senha:")); 
        add(txtSenha = new JPasswordField());
        
        add(new JLabel("Cargo:"));
        cbCargo = new JComboBox<>(new String[]{"Selecione...", "Admin", "Bibliotecário"});
        add(cbCargo);

        add(new JLabel("")); // vazio
        add(btnSalvar = new JButton("Cadastrar Funcionário"));

        // oq o botão vai fazer
        btnSalvar.addActionListener(e -> {
            try {
                controller.salvar(
                    txtNome.getText(), 
                    txtCpf.getText(), 
                    txtLogin.getText(), 
                    new String(txtSenha.getPassword()), 
                    (String) cbCargo.getSelectedItem()
                );
                
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
                limparCampos();
                
            } catch (ValidacaoException ex) {
                // exceptions
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void limparCampos() {
        txtNome.setText(""); txtCpf.setText(""); 
        txtLogin.setText(""); txtSenha.setText("");
        cbCargo.setSelectedIndex(0);
    }
}

