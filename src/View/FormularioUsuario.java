package View;

import Controller.UsuarioController;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Janela modal responsável pelo cadastro e edição de usuários no sistema.
 * Permite a entrada de Nome, CPF, Endereço e Data de Nascimento,
 * realizando validação visual antes de enviar os dados ao controller.
 *
 * Aceita datas exclusivamente no formato brasileiro (DD/MM/AAAA).
 *
 * Esta tela é utilizada pelo PainelGestaoUsuarios.
 *
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Henrique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class FormularioUsuario extends JDialog {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JTextField txtDataNascimento;

    private UsuarioController controller;
    private PainelGestaoUsuarios painel;

    private Usuario usuarioEditando;

    // Formato brasileiro DD/MM/AAAA
    private final DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor da janela de formulário.
     *
     * @param usuario Usuário existente (modo edição) ou null (modo cadastro).
     * @param controller Controller responsável pelas regras de negócio.
     * @param painel Painel pai que deve atualizar a tabela após salvar.
     */
    public FormularioUsuario(Usuario usuario, UsuarioController controller, PainelGestaoUsuarios painel) {
        this.usuarioEditando = usuario;
        this.controller = controller;
        this.painel = painel;

        setTitle(usuario == null ? "Cadastrar Usuário" : "Editar Usuário");
        setSize(400, 300);
        setModal(true);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

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

        // Preencher dados na edição
        if (usuario != null) {
            txtNome.setText(usuario.getNome());
            txtCpf.setText(usuario.getCpf());
            txtCpf.setEnabled(false);
            txtEndereco.setText(usuario.getEndereco());
            txtDataNascimento.setText(usuario.getDataNascimento().format(formatoBR));
        }

        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> salvar());
    }

    /**
     * Lê os campos do formulário, valida a data e envia ao controller.
     * Após salvar com sucesso, atualiza a tabela do painel pai.
     */
    private void salvar() {
        try {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String endereco = txtEndereco.getText().trim();
            String dataStr = txtDataNascimento.getText().trim();

            if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || dataStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate dataNascimento;
            try {
                dataNascimento = LocalDate.parse(dataStr, formatoBR);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida! Use DD/MM/AAAA.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

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
