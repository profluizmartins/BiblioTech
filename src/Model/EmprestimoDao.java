package Model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Classe que irá gerenciar os empréstimos
 * @author Arthur
 * @author henrique
 * @author Usuário
 * @author ericu
 * @author O anão
 * @version 1.0
 */


public class EmprestimoDao {
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private int proximoId = 1;
    private LocalDate dataAtual = LocalDate.now();
    private int idItem;

    public void adicionarEmprestimo(Usuario usuario, String itemEmprestado,
                                 boolean statusDevolvido, boolean statusAtrasado,
                                 LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        emprestimos.add(new Emprestimo(proximoId++, usuario, itemEmprestado,
                statusDevolvido, statusAtrasado, dataEmprestimo, dataDevolucao));
    } // ACHO QUE NÃO PRECISA FAZER ESSE ARRAYLIST




    public static Emprestimo buscarPorId(int idItem) {
        for (Emprestimo e : emprestimos) {
            if (e.getIdItem() == idItem /*&& e.getStatusDevolvido()*/ ){
                return e;
            }
        }
        return null;
    }

    public int getProximoId() {
        return proximoId;
    }

    public void setProximoId(int proximoId) {
        this.proximoId = proximoId;
    }


    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

}



