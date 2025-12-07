package View;

import Controller.DevolucaoController;
import Exceptions.EmprestimoNaoEncontradoException;
import model.MockEmprestimo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe para criar uma interface de devolução de itens da biblioteca
 * @author Rômulo Henrique
 * @author Arthur Souza Santos
 * @author Samuel Barbosa
 * @author Eric Felipe
 * @author Thiago Tomáz
 * @version 1.1
 */
public class PainelDevolucao extends JPanel {

    private JPanel informationPanel;
    private JPanel buttonsPanel;
    private JTextField txtIdItem;
    private JLabel idDevolucao;
    private JTextArea areaDetalhes;
    private JButton btnConfirmar;
    private JButton btnConfirmarDevolucao;

    /**
     * Método construtor do painel a ser visualizado
     */
    public PainelDevolucao() {
        this.setLayout(new BorderLayout(10, 10));

        informationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        idDevolucao = new JLabel("Id do Item:");
        txtIdItem = new JTextField(15);
        btnConfirmar = new JButton("Buscar Item");

        informationPanel.add(idDevolucao);
        informationPanel.add(txtIdItem);
        informationPanel.add(btnConfirmar);

        this.add(informationPanel, BorderLayout.NORTH);

        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        areaDetalhes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollDetalhes = new JScrollPane(areaDetalhes);
        scrollDetalhes.setBorder(new TitledBorder("Detalhes do Empréstimo"));

        this.add(scrollDetalhes, BorderLayout.CENTER);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnConfirmarDevolucao = new JButton("Confirmar Devolução");
        btnConfirmarDevolucao.setEnabled(false);
        btnConfirmarDevolucao.setBackground(new Color(144, 238, 144));

        buttonsPanel.add(btnConfirmarDevolucao);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /**
     * Método que mostra ao usuário os status do item do acervo na tela de detalhes.
     *
     * Ele atualiza a interface e habilita o botão de confirmação.
     * @param emprestimos O objeto com os dados do empréstimo
     * @return Retorna 1 se o item foi encontrado e exibido
     * @throws EmprestimoNaoEncontradoException caso não exista emprestimo
     */
    public int mostrarEmprestimo(MockEmprestimo emprestimos) throws EmprestimoNaoEncontradoException {
        if (emprestimos != null) {
            String detalhes =
                    "ID: " + emprestimos.getIdItem() + "\n" +
                            "Usuário: " + emprestimos.getUsuario().getNome() + "\n" +
                            "Item: " + emprestimos.getItemEmprestado() + "\n" +
                            "Status devolvido: " + emprestimos.getStatusDevolvido() + "\n" +
                            "Status atrasado: " + emprestimos.getStatusAtrasado() + "\n" +
                            "Data do empréstimo: " + emprestimos.getDataEmprestimo() + "\n" +
                            "Data da devolução: " + emprestimos.getDataDevolucao() + "\n" +
                            "--------------------------------------------------\n" +
                            "Verifique os dados acima antes de confirmar.";

            areaDetalhes.setText(detalhes);
            btnConfirmarDevolucao.setEnabled(true);
            return 1;
        } else {
            throw new EmprestimoNaoEncontradoException("Empréstimo não encontrado para este ID!");
        }
    }

    /**
     * Método que adiciona um listener ao botão de busca ("Buscar Item")
     * @param listener
     */
    public void addBtnConfirmarListener(ActionListener listener) {
        this.btnConfirmar.addActionListener(listener);
    }

    /**
     * Método novo necessário para capturar a ação do botão "Confirmar Devolução"
     * que agora fica na tela principal.
     * @param listener
     */
    public void addBtnConfirmarDevolucaoListener(ActionListener listener) {
        this.btnConfirmarDevolucao.addActionListener(listener);
    }

    /**
     * Método para pegar o que o usuário digitou no campo de texto do identificador do item
     * @return Retorna o texto digitado
     */
    public String gettxtIdItem(){
        return txtIdItem.getText();
    }

    /**
     * Método para limpar o campo de texto do identificador do item e a área de detalhes
     */
    public void limparcampoID(){
        txtIdItem.setText("");
        areaDetalhes.setText("");
        btnConfirmarDevolucao.setEnabled(false);
    }

    /**
     * Método para criar um painel de diálogo ao usuário
     * @param mensagem Mensagem a ser exibida
     * @param titulo Título da mensagem a ser exibido
     */
    public void msg(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}