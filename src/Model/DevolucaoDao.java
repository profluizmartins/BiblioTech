package Model;

import javax.swing.*;
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
public class DevolucaoDao {

    /**
     * Método que calcula quantos dias há de atraso
     *
     * @param emprestimo Emprestimo do item
     *
     * @return Retorna quantos dias há de atraso, ou retorna 0 se não há dias de atraso
     */
    public int CalcularAtraso(Emprestimo emprestimo) {
       LocalDate dataAtual = LocalDate.now();
       LocalDate dataDevolucao = emprestimo.getDataDevolucao();
       boolean statusAtrasado = emprestimo.getStatusDevolvido();

        if (dataDevolucao.isBefore(dataAtual)) {
            emprestimo.setStatusAtrasado(true);
            long diferenca = ChronoUnit.DAYS.between(dataDevolucao, dataAtual);
            return(int) diferenca;
        } else {
            emprestimo.setStatusAtrasado(false);
        }
        return 0;
    }
}
