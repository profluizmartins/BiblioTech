package View;

import Controller.DevolucaoController;
import Model.Emprestimo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe para criar uma interface de devolução de itens da biblioteca
 * @author Rômulo Henrique
 * @author Arthur Souza Santos
 * @author Samuel Barbosa
 * @author Eric Felipe
 * @author Thiago Tomáz
 * @version 1.0
 */
public class PainelDevolucao extends JPanel {

    private JPanel informationPanel;
    private JTextField txtIdItem;
    private JLabel idDevolucao;
    private JLabel usuario;
    private JLabel itemEmprestado;
    private JTextArea areaDetalhes;
    private DevolucaoController controller;
    private JButton btnConfirmar, btnConfirmarDevolucao;

    /**
     * Método construtor do painel a ser visualizado
     */
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

    /**
     * Método que mostra ao usuário os status do item do acervo
     * @param emprestimos
     * @return Retorna os dados do item consultado caso encontrado,
     * se não encontrado retorna uma caixa de erro informando que o item não foi encontrado
     */
    public int mostrarEmprestimo(Emprestimo emprestimos) {
        if (emprestimos != null) {
            String [] confirmacoes={"Confirmar Devolucao","Cancelar"};
            int resposta=JOptionPane.showOptionDialog(null,
                    "ID: " + emprestimos.getIdItem()+"\n"+
                            "Usuário: " + emprestimos.getUsuario().getNome()+"\n"+
                            "Item:" + emprestimos.getItemEmprestado()+"\n"+
                            "Status devolvido:" + emprestimos.getStatusDevolvido()+"\n"+
                            "Status atrasado:" + emprestimos.getStatusAtrasado()+"\n"+
                            "Data do empréstimo:" + emprestimos.getDataEmprestimo()+"\n"+
                            "Data da devolução:" + emprestimos.getDataDevolucao()+"\n"+
                            "---------------",
                    "Estoque",0,
                    JOptionPane.INFORMATION_MESSAGE,null,confirmacoes,confirmacoes[0]);
                return resposta;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Item não encontrado.",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }

    /**
     * Método que adiciona um listenner ao botão "Confirmar"
     * @param listener
     */
    public void addBtnConfirmarListener(ActionListener listener) {
        this.btnConfirmar.addActionListener(listener);
    }

    /**
     * Método para pegar o que o usuário digitou no campo de texto do identificador do item
     * @return Retorna o texto digitado
     */
    public String gettxtIdItem(){
        return txtIdItem.getText();
    }

    /**
     * Método para limpar o campo de texto do identificador do item
     */
    public void limparcampoID(){
        txtIdItem.setText("");
    }

    /**
     * Método para criar um painel de diálogo ao usuário
     * @param mensagem Mensagem a ser exibida
     * @param titulo Título da mensagem a ser exibido
     */
    public void msg(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

}
