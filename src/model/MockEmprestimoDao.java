package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class MockEmprestimoDao {
    private static ArrayList<MockEmprestimo> mockEmprestimos = new ArrayList<MockEmprestimo>();
    private LocalDate dataAtual = LocalDate.now();
    private int idItem;

   public void adicionarEmprestimo(MockItemAcervo item, MockUsuario mockUsuario,
                                   LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        mockEmprestimos.add(new MockEmprestimo(item, mockUsuario, item.getTitulo(),
                item.isStatusDisponivel(), dataEmprestimo, dataDevolucao));
    } // ACHO QUE NÃO PRECISA FAZER ESSE ARRAYLIST

    public static MockEmprestimo buscarPorId(int idItem) {
        for (MockEmprestimo e : mockEmprestimos) {
            if (e.getIdItem() == idItem /*&& e.getStatusDevolvido()*/ ){
                return e;
            }
        }
        return null;
    }

    public List<MockEmprestimo> listarProduto(){
        return mockEmprestimos;
    }

    public ArrayList<MockEmprestimo> getEmprestimos() {
        return mockEmprestimos;
    }

    public void setEmprestimos(ArrayList<MockEmprestimo> mockEmprestimos) {
        this.mockEmprestimos = mockEmprestimos;
    }

}



