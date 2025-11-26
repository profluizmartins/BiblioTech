package View;

import javax.swing.*;
import java.awt.*;

// Import necessário para achar o Controller
import Controller.FormLivroController;

public class FormularioLivro extends JDialog {

    private FormLivroController controller;

    private JComboBox<String> comboTipo;
    private JTextField txtTitulo;
    private JTextField txtAno;
    private JComboBox<String> comboStatus;
    
    // Campos Livro
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditora;
    
    // Campos Revista
    private JTextField txtEdicao;
    private JTextField txtPeriodicidade;

    private JPanel painelLivro;
    private JPanel painelRevista;

    public FormularioLivro(Frame dono) {
        super(dono, "Novo Item do Acervo", true); 
        this.setSize(400, 500);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(dono); 
        
        this.controller = new FormLivroController();

        // 1. PAINEL FORMULÁRIO
        JPanel formulario = new JPanel(new GridLayout(0, 1, 5, 5)); 
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formulario.add(new JLabel("Tipo de Item:"));
        comboTipo = new JComboBox<>(new String[]{"Livro", "Revista"});
        formulario.add(comboTipo);

        formulario.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        formulario.add(txtTitulo);

        formulario.add(new JLabel("Ano de Publicação:"));
        txtAno = new JTextField();
        formulario.add(txtAno);
        
        formulario.add(new JLabel("Status Inicial:"));
        comboStatus = new JComboBox<>(new String[]{"Disponível", "Emprestado", "Reservado", "Descartado"});
        formulario.add(comboStatus);

        // Painel Livro
        painelLivro = new JPanel(new GridLayout(0, 1));
        painelLivro.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        painelLivro.add(txtAutor);
        painelLivro.add(new JLabel("ISBN (13 dígitos):"));
        txtIsbn = new JTextField();
        painelLivro.add(txtIsbn);
        painelLivro.add(new JLabel("Editora:"));
        txtEditora = new JTextField();
        painelLivro.add(txtEditora);
        formulario.add(painelLivro);

        // Painel Revista
        painelRevista = new JPanel(new GridLayout(0, 1));
        painelRevista.add(new JLabel("Edição (Número):"));
        txtEdicao = new JTextField();
        painelRevista.add(txtEdicao);
        painelRevista.add(new JLabel("Periodicidade (Dias):"));
        txtPeriodicidade = new JTextField();
        painelRevista.add(txtPeriodicidade);
        formulario.add(painelRevista);
        painelRevista.setVisible(false); 

        this.add(new JScrollPane(formulario), BorderLayout.CENTER);

        // 2. BOTÕES
        JPanel painelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        this.add(painelBotoes, BorderLayout.SOUTH);

        // 3. AÇÕES
        comboTipo.addActionListener(e -> {
            if (comboTipo.getSelectedItem().equals("Livro")) {
                painelLivro.setVisible(true);
                painelRevista.setVisible(false);
            } else {
                painelLivro.setVisible(false);
                painelRevista.setVisible(true);
            }
            revalidate(); 
        });

        btnSalvar.addActionListener(e -> {
            try {
                String tipo = (String) comboTipo.getSelectedItem();
                
                if (tipo.equals("Livro")) {
                    controller.salvarLivro(
                        txtTitulo.getText(),
                        txtAno.getText(),
                        (String) comboStatus.getSelectedItem(),
                        txtAutor.getText(),
                        txtIsbn.getText(),
                        txtEditora.getText()
                    );
                } else {
                    controller.salvarRevista(
                        txtTitulo.getText(),
                        txtAno.getText(),
                        (String) comboStatus.getSelectedItem(),
                        txtEdicao.getText(),
                        txtPeriodicidade.getText()
                    );
                }

                JOptionPane.showMessageDialog(this, "Item salvo com sucesso!");
                dispose(); 
                
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}