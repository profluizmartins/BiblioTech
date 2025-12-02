package View;

import Controller.UsuarioController;
import Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormularioUsuario extends JDialog {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JTextField txtDataNascimento;

    private UsuarioController controller;
    private PainelGestaoUsuarios painel;

    private Usuario usuarioEditando;

    // Padrão brasileiro DD/MM/AAAA
    private final DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FormularioUsuario(Usuario usuario, UsuarioController controller, PainelGestaoUsuarios painel) {
        this.usuarioEditando = usuario;
        this.controller = controller;
        this.painel = painel;

        setTitle(usuario == null ? "Cadastrar Usuário" : "Editar Usuário");
        setSize(400, 300);
        setModal(true);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        
        // CAMPOS

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        add(txtCpf);

        add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        add(txtEndereco);

        add(new JLabel("Nascimento (DD/MM/AAAA):"));
        txtDataNascimento = new JTextField();
        add(txtDataNascimento);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnSalvar);
        add(btnCancelar);

        // EDIÇÃO

        if (usuario != null) {
            txtNome.setText(usuario.getNome());
            txtCpf.setText(usuario.getCpf());
            txtCpf.setEnabled(false); // CPF não altera
            txtEndereco.setText(usuario.getEndereco());
            txtDataNascimento.setText(usuario.getDataNascimento().format(formatoBR));
        }

        // LISTENERS

        btnCancelar.addActionListener(e -> dispose());

        btnSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {
        try {
            // Lê campos
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String endereco = txtEndereco.getText().trim();
            String dataStr = txtDataNascimento.getText().trim();

            // VALIDAÇÕES MANUAIS

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O CPF é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (endereco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O endereço é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (dataStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A data de nascimento é obrigatória.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // PARSE DE DATA (APÓS VALIDAÇÃO)

            LocalDate dataNascimento;
            try {
                dataNascimento = LocalDate.parse(dataStr, formatoBR);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Data inválida! Use o formato DD/MM/AAAA.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // CONTROLLER

            if (usuarioEditando == null) {
                controller.criarUsuario(nome, cpf, endereco, dataNascimento);
            } else {
                controller.atualizarUsuario(nome, cpf, endereco, dataNascimento);
            }

            painel.carregarTabela();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
