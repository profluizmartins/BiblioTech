package View;

import Controller.UsuarioController;
import Model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Painel principal do módulo de Gestão de Usuários.
 * Contém a tabela de listagem e botões para cadastrar,
 * editar, excluir e atualizar usuários.
 *
 * É responsável por interagir diretamente com o controller
 * e atualizar a interface com os dados mais recentes.
 *
 * Implementa a interface IModulo para padronização de módulos do sistema.
 * 
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Henrique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class PainelGestaoUsuarios extends JPanel implements IModulo {

    private UsuarioController controller;
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;

    /**
     * Construtor responsável por inicializar os componentes visuais
     * e carregar os dados iniciais da tabela.
     */
    public PainelGestaoUsuarios() {

        controller = new UsuarioController();
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestão de Usuários");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
                new Object[]{"Matrícula", "Nome", "CPF", "Endereço", "Idade", "Status"},
                0
        );
        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabelaUsuarios);
        add(scroll, BorderLayout.CENTER);

        carregarTabela();

        JPanel botoes = new JPanel(new FlowLayout());

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar");

        botoes.add(btnCadastrar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);

        add(botoes, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> {
            FormularioUsuario form = new FormularioUsuario(null, controller, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int index = tabelaUsuarios.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String cpf = modeloTabela.getValueAt(index, 2).toString();
            Usuario usuario = controller.buscarUsuarioPorCpf(cpf);

            FormularioUsuario form = new FormularioUsuario(usuario, controller, this);
            form.setVisible(true);
        });

        btnExcluir.addActionListener(e -> {
            int index = tabelaUsuarios.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String cpf = modeloTabela.getValueAt(index, 2).toString();
            controller.excluirUsuario(cpf);
            carregarTabela();
        });

        btnAtualizar.addActionListener(e -> carregarTabela());
    }

    /**
     * Retorna o painel do módulo.
     *
     * @return JPanel deste módulo.
     */
    @Override
    public JPanel getPanel() {
        return this;
    }

    /**
     * Recarrega todas as linhas da tabela com base nos dados atuais do controller.
     */
    public void carregarTabela() {
        modeloTabela.setRowCount(0);
        List<Usuario> lista = controller.listarUsuarios();

        for (Usuario u : lista) {
            modeloTabela.addRow(new Object[]{
                    u.getMatricula(),
                    u.getNome(),
                    u.getCpf(),
                    u.getEndereco(),
                    u.calcularIdade(),
                    u.getStatus()
            });
        }
    }
}
