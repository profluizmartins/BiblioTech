package Model;
import Model.EmprestimoDao;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DevolucaoDao {



    public void CalcularAtraso(LocalDate dataAtual, LocalDate dataDevolucao, boolean statusAtrasado) {
        if (dataDevolucao.isBefore(dataAtual)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucao, dataAtual);
            statusAtrasado = true;
        } else {

            statusAtrasado = false;
        }
    }
}
