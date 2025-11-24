package Model;
import Model.EmprestimoDao;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DevolucaoDao {
    public void realizarDevolucao(int idItem) {
        int id = 0;
        double valordamulta = 0;
        int diasAtraso = 0;


    }





    public int CalcularAtraso(Emprestimo emprestimo) {
       LocalDate dataAtual = LocalDate.now();
       LocalDate dataDevolucao = emprestimo.getDataDevolucao();
       boolean statusAtrasado = emprestimo.getStatusDevolvido();


        if (dataDevolucao.isBefore(dataAtual)) {
            emprestimo.setStatusAtrasado(true);
            long diferença = ChronoUnit.DAYS.between(dataDevolucao, dataAtual);
            return(int) diferença;

        } else {

            emprestimo.setStatusAtrasado(false);

        }
        return 0;
    }
}
