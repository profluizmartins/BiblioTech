package model;

import java.util.List;

/**
 * Interface responsável por definir as operações de consulta ao acervo do sistema.
 * Esta interface não possui implementação neste módulo, sendo fornecida pelo
 * Módulo 2 (Gestão de Acervo). Seu propósito aqui é permitir que o Módulo 8
 * realize buscas sem conhecer detalhes internos da persistência.
 *
 * O uso desta interface garante baixo acoplamento entre módulos e segue o
 * princípio de inversão de dependência, atendendo à arquitetura MVC utilizada
 * no desenvolvimento do sistema.
 */
public interface IAcervoRepositorio {

    /**
     * Busca itens no acervo a partir de um título ou parte dele.
     *
     * @param titulo Texto utilizado como critério de busca.
     * @return Lista contendo os itens do acervo que atendem ao filtro especificado.
     */
    List<Object> buscarPorTitulo(String titulo);

    /**
     * Busca itens no acervo pelo nome do autor.
     *
     * @param autor Nome completo ou parcial do autor.
     * @return Lista de itens publicados pelo autor informado.
     */
    List<Object> buscarPorAutor(String autor);

    /**
     * Busca itens do acervo pelo ano de publicação.
     *
     * @param ano Ano exato utilizado como filtro de pesquisa.
     * @return Lista contendo os itens compatíveis com o ano informado.
     */
    List<Object> buscarPorAno(int ano);

    /**
     * Retorna todos os itens cadastrados no acervo, sem aplicação de filtros.
     * Este método é utilizado em relatórios gerais e na carga inicial da tabela
     * de busca avançada.
     *
     * @return Lista contendo todos os registros do acervo.
     */
    List<Object> listarTodos();
}