package Controller;

import Exceptions.EmprestimoNaoEncontradoException;
import Exceptions.ItemJaDevolvidoException;
import Model.*;
import View.PainelDevolucao;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe Controller de Devolução
 *
 * @author Rômulo Henrique
 * @author Arthur Souza Santos
 * @author Samuel Barbosa
 * @author Eric Felipe
 * @author Thiago Tomaz
 *
 * @version 1.0
 */
public class DevolucaoController {
    private EmprestimoDao Edao;
    private DevolucaoDao dao;
    private PainelDevolucao view;
    private MultaController multacontrol;
    private Emprestimo emprestimo;

    /**
     * Método construtor da classe DevolucaoController
     *
     * @param dao Dao do painel de devolução
     * @param view View do painel de devolução
     * @param multacontrol Controller da multa
     *
     */
    public DevolucaoController(DevolucaoDao dao, PainelDevolucao view, MultaController multacontrol, ItemAcervo acervo) {
        this.dao = dao;
        this.view = view;
        this.multacontrol = multacontrol;
        this.emprestimo = null;


        this.view.addBtnConfirmarListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                try {
                    int id = Integer.parseInt(view.gettxtIdItem());
                    double valordamulta= 0;
                    int resposta=view.mostrarEmprestimo(Edao.buscarPorId(id));
                    if(resposta==0){
                        try {
                            valordamulta = realizarDevolucao(id, acervo);
                            if (valordamulta > 0) {
                                view.msg("Multa: R$ " + valordamulta, "titulo");
                            }
                        } catch (ItemJaDevolvidoException ex) {
                            throw new RuntimeException(ex);
                        }
                        view.limparcampoID();
                    }
                } catch (NumberFormatException ex) {
                    view.msg( "Digite um número válido.", "Numero inválido");
                }}
        });
    }

    /**
     * Método que realiza a devolução de um item
     *
     * @param idItem Item do acervo a ser devolvido
     *
     * @throws ItemJaDevolvidoException Quando um item já foi devolvido
     *
     * @return valor da multa, se houver uma multa
     */
    public double realizarDevolucao(int idItem, ItemAcervo acervo) throws ItemJaDevolvidoException{
        int id = 0;
        double valordamulta = 0;
        int diasAtraso = 0;

        Emprestimo emprestimo = EmprestimoDao.buscarPorId(idItem);
        try {
            if (emprestimo == null) {
                JOptionPane.showMessageDialog(null, "Emprestimo não encontrado");
                return 0;
            } else if (emprestimo.getStatusDevolvido()) {
                throw new ItemJaDevolvidoException("O Item já foi devolvido.");
            }
        }catch(ItemJaDevolvidoException ex){
            view.msg("O Item já foi devolvido.","Mensagem de erro");
        }
        diasAtraso = dao.CalcularAtraso(emprestimo);Usuario usuario = emprestimo.getUsuario();
        valordamulta = multacontrol.calcularMulta(usuario, diasAtraso);

        emprestimo.setStatusDevolvido(true);
        acervo.setStatusEmprestado(false);
        return valordamulta;
    }
}