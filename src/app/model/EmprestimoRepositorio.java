package app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por armazenar e manipular os empréstimos.
 * Funciona como um "banco de dados" interno em memória.
 * 
 * @author Grupo 4
 * @version 1.0
 */
public class EmprestimoRepositorio {

    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Cria e adiciona um novo empréstimo ao repositório.
     */
    public Emprestimo criarEmprestimo(Usuario usuario, ItemAcervo item, java.time.LocalDate dataEmprestimo, java.time.LocalDate dataPrevistaDevolucao) {

        Emprestimo emp = new Emprestimo(proximoId++, usuario, item, dataEmprestimo, dataPrevistaDevolucao, StatusEmprestimo.ATIVO);

        emprestimos.add(emp);
        return emp;
    }

    /**
     * Retorna todos os empréstimos cadastrados.
     */
    public List<Emprestimo> listar() {
        return emprestimos;
    }

    /**
     * Busca um empréstimo pelo ID.
     */
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}