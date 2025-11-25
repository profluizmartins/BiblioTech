package Model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class EmprestimoDao {
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private LocalDate dataAtual = LocalDate.now();
    private int idItem;

   public void adicionarEmprestimo(ItemAcervo item, Usuario usuario,
                                 LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        emprestimos.add(new Emprestimo(item, usuario, item.getTitulo(),
                item.isStatusDisponivel(), dataEmprestimo, dataDevolucao));
    } // ACHO QUE NÃO PRECISA FAZER ESSE ARRAYLIST

    public static Emprestimo buscarPorId(int idItem) {
        for (Emprestimo e : emprestimos) {
            if (e.getIdItem() == idItem /*&& e.getStatusDevolvido()*/ ){
                return e;
            }
        }
        return null;
    }

    public List<Emprestimo> listarProduto(){
        return emprestimos;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

}



