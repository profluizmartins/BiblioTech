package app.view;

import app.controller.EmprestimoController;
import app.model.Usuario;
import app.model.ItemAcervo;
import app.controller.EmprestimoController;


import javax.swing.*;
import java.awt.*;

public class PainelNovoEmprestimo extends JPanel {

    private JTextField txtMatricula;
    private JTextField txtItemId;
    private JTextArea txtDetalhes;
    private JButton btnBuscarUsuario;
    private JButton btnBuscarItem;
    private JButton btnConfirmar;

    private EmprestimoController controller;

    public PainelNovoEmprestimo(EmprestimoController controller) {

        this.controller = controller;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel Superior (campos e botões)
        JPanel painelCampos = new JPanel(new GridLayout(3, 3, 10, 10));

        painelCampos.add(new JLabel("Matrícula do Usuário:"));
        txtMatricula = new JTextField();
        painelCampos.add(txtMatricula);

        btnBuscarUsuario = new JButton("Buscar Usuário");
        painelCampos.add(btnBuscarUsuario);

        painelCampos.add(new JLabel("ID do Item:"));
        txtItemId = new JTextField();
        painelCampos.add(txtItemId);

        btnBuscarItem = new JButton("Buscar Item");
        painelCampos.add(btnBuscarItem);

        btnConfirmar = new JButton("Confirmar Empréstimo");
        painelCampos.add(new JLabel());
        painelCampos.add(new JLabel());
        painelCampos.add(btnConfirmar);

        add(painelCampos, BorderLayout.NORTH);

        // Área de detalhes (feedback ao usuário)
        txtDetalhes = new JTextArea();
        txtDetalhes.setEditable(false);
        txtDetalhes.setFont(new Font("monospaced", Font.PLAIN, 14));
        txtDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes da Busca"));

        add(new JScrollPane(txtDetalhes), BorderLayout.CENTER);

        configurarEventos();
    }

    private void configurarEventos() {

        // ✦ Botão Buscar Usuário
    	btnBuscarUsuario.addActionListener(e -> {
    	    try {
    	        Usuario u = controller.buscarUsuario(txtMatricula.getText());
    	        txtDetalhes.setText(
    	                "✓ Usuário encontrado:\n" +
    	                "Nome: " + u.getNome() +
    	                "\nStatus: " + u.getStatus()
    	        );
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	    }
    	});

        // ✦ Botão Buscar Item
        btnBuscarItem.addActionListener(e -> {
            try {
                ItemAcervo item = controller.buscarItem(txtItemId.getText());
                txtDetalhes.append(
                        "\nItem: " + item.getTitulo() +
                        " | Status: " + item.getStatus()
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ✦ Botão Confirmar Empréstimo
        btnConfirmar.addActionListener(e -> {
            try {
                controller.confirmarEmprestimo();
                txtDetalhes.append("\n\n✓ Empréstimo realizado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
