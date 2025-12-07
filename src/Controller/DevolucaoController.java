package Controller;

import Exceptions.EmprestimoNaoEncontradoException;
import Exceptions.ItemJaDevolvidoException;
import model.*;
import View.PainelDevolucao;
import model.entites.Devolucao;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private MockEmprestimoDao Edao;
    private Devolucao dao;
    private PainelDevolucao view;
    private MockMultaController multacontrol;
    private MockEmprestimo mockEmprestimo;
    private MockItemAcervo acervo;
    private List<MockItemAcervo> listaAcervo;
    /**
     * Método construtor da classe DevolucaoController
     *
     * @param dao Dao do painel de devolução
     * @param view View do painel de devolução
     * @param multacontrol Controller da multa
     *
     */

    private MockItemAcervo itemParaDevolucao = null;
    private int idParaDevolucao = -1;

    public DevolucaoController(Devolucao dao, PainelDevolucao view, MockMultaController multacontrol, List<MockItemAcervo> listaAcervo) {
        this.dao = dao;
        this.view = view;
        this.multacontrol = multacontrol;
        this.listaAcervo = listaAcervo;


        this.view.addBtnConfirmarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idDigitado = Integer.parseInt(view.gettxtIdItem());
                    MockItemAcervo itemEncontradoTemp = null;
                    for (MockItemAcervo item : listaAcervo) {
                        if (item.getId() == idDigitado) {
                            itemEncontradoTemp = item;
                            break;
                        }
                    }
                    if (itemEncontradoTemp == null) {
                        view.msg("Item com ID " + idDigitado + " não existe no acervo.", "Erro");
                        return;
                    }
                    var emprestimo = Edao.buscarPorId(idDigitado);
                    view.mostrarEmprestimo(emprestimo);
                    itemParaDevolucao = itemEncontradoTemp;
                    idParaDevolucao = idDigitado;
                } catch (EmprestimoNaoEncontradoException ex) {
                    view.msg(ex.getMessage(), "Erro na Busca");
                    view.limparcampoID();
                    itemParaDevolucao = null;
                    idParaDevolucao = -1;
                } catch (NumberFormatException ex) {
                    view.msg("Digite um número válido.", "Numero inválido");
                }
            }
        });

        this.view.addBtnConfirmarDevolucaoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemParaDevolucao != null && idParaDevolucao != -1) {
                    try {
                        realizarDevolucao(idParaDevolucao, itemParaDevolucao);
                        view.limparcampoID();
                        itemParaDevolucao = null;
                        idParaDevolucao = -1;
                    } catch (ItemJaDevolvidoException ex) {
                        view.msg("Este item já foi devolvido anteriormente.", "Aviso");
                    } catch (RuntimeException ex) {
                        view.msg("Erro ao realizar devolução: " + ex.getMessage(), "Erro");
                    }
                } else {
                    view.msg("Por favor, busque um item válido primeiro.", "Aviso");
                }
            }
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
    public double realizarDevolucao(int idItem, MockItemAcervo acervo) throws ItemJaDevolvidoException {
        int id = 0;
        double valordamulta = 0;
        int diasAtraso = 0;

        MockReserva reserva = null;
        MockEmprestimo mockEmprestimo = MockEmprestimoDao.buscarPorId(idItem);

        try {
            if (mockEmprestimo == null) {
                throw new EmprestimoNaoEncontradoException("Devolução falhou: Não há nenhum empréstimo ativo registrado para o item com ID " + idItem);
            } else if (mockEmprestimo.getStatusDevolvido()) {
                throw new ItemJaDevolvidoException("O Item já foi devolvido.");
            }
        } catch (ItemJaDevolvidoException ex) {
            view.msg("Este item já consta como devolvido no sistema.", "Mensagem de erro");
        } catch (EmprestimoNaoEncontradoException ex) {
            view.msg(ex.getMessage(), "Erro: Empréstimo não encontrado");
            return 0;
        }

        if (!mockEmprestimo.getStatusDevolvido()) {
            mockEmprestimo.setStatusDevolvido(true);
            diasAtraso = dao.CalcularAtraso(mockEmprestimo);
            MockUsuario mockUsuario = mockEmprestimo.getUsuario();
            valordamulta = multacontrol.calcularMulta(mockUsuario, diasAtraso);
            /*if(reserva.getStatus() == 1){
                acervo.setStatusReservado(true);
                acervo.setStatusDisponivel(false);
                view.msg("Devolução registrada. O item " + acervo.getTitulo() + " foi automaticamente direcionado\n" +
                        "para a reserva do usuário " + reserva.getUsuario(), "Sucesso");
            }*/
            acervo.setStatusReservado(false);
            acervo.setStatusDisponivel(true);
            view.msg("Devolução registrada com sucesso para o item " + acervo.getTitulo(), "Sucesso");
            if (valordamulta > 0) {
                view.msg("Devolução registrada com ATRASO! Uma multa de R$ " + valordamulta + " foi gerada para o usuário " + mockUsuario.getNome(), "Multa por atraso");
            }
            acervo.setStatusEmprestado(false);
        }
        return valordamulta;
    }
}