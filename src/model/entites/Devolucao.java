package model.entites;

import model.Emprestimo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe Dao de Devolução
 *
 * @author Rômulo Henrique
 * @author Arthur Souza Santos
 * @author Samuel Barbosa
 * @author Eric Felipe
 * @author Thiago Tomáz
 *
 * @version 1.0
 */
public class Devolucao {

    /**
     * Método que calcula quantos dias há de atraso
     *
     * @param Emprestimo Emprestimo do item
     *
     * @return Retorna quantos dias há de atraso, ou retorna 0 se não há dias de atraso
     */
    public int CalcularAtraso(Emprestimo Emprestimo) {
       LocalDate dataAtual = LocalDate.now();
       LocalDate dataDevolucao = Emprestimo.getDataDevolucao();
       boolean statusAtrasado = Emprestimo.getStatusDevolvido();

        if (dataDevolucao.isBefore(dataAtual)) {
            Emprestimo.setStatusAtrasado(true);
            long diferenca = ChronoUnit.DAYS.between(dataDevolucao, dataAtual);
            return(int) diferenca;
        } else {
            Emprestimo.setStatusAtrasado(false);
        }
        return 0;
    }
}
