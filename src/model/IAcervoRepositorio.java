package model;
import java.util.List;

/**
 * Parte que precisa do Grupo 2, isso é só a Estrutura que a gente vai usar de base
 */

public interface IAcervoRepositorio {

    /**
     * Busca itens no acervo pelo título (parcial ou completo).
     * @param titulo O título ou parte dele para busca.
     * @return Uma lista de objetos (Livros/Revistas) encontrados ou lista vazia.
     */
    List<Object> buscarPorTitulo(String titulo);

    /**
     * Busca itens no acervo pelo nome do autor.
     * @param autor O nome do autor para busca.
     * @return Uma lista de itens encontrados.
     */
    List<Object> buscarPorAutor(String autor);

    /**
     * Busca itens no acervo pelo ano de publicação.
     * @param ano O ano de publicação.
     * @return Uma lista de itens encontrados.
     */
    List<Object> buscarPorAno(int ano);

    /**
     * Retorna a lista completa de itens cadastrados.
     * Útil para relatórios gerais.
     * @return Lista com todos os itens do acervo.
     */
    List<Object> listarTodos();
}