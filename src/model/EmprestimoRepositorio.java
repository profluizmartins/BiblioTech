package model;
import java.util.List;
import java.util.Map;

/**
 * Parte que precisa do Grupo 4, isso é só a Estrutura que a gente vai usar de base
 */

public interface EmprestimoRepositorio {

    /**
     * Lista todos os empréstimos que estão atualmente em atraso.
     * (Data prevista de devolução é anterior à data atual e status é Ativo).
     * @return Lista de empréstimos atrasados.
     */
    List<Object> listarEmprestimosEmAtraso();

    /**
     * Retorna estatísticas sobre os itens mais emprestados.
     * @return Um Mapa onde a chave é o Título do Item e o valor é a quantidade de empréstimos.
     */
    Map<String, Integer> obterEstatisticasItensMaisEmprestados();
}