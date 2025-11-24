package model;
import java.util.List;

/**
 * Parte que precisa do Grupo 3, isso é só a Estrutura que a gente vai usar de base
 */

public interface IUsuarioRepositorio {

    /**
     * Lista todos os usuários que possuem multas pendentes.
     * Usado para o relatório de inadimplência.
     * @return Lista de usuários com status 'SuspensoPorMulta' ou com débitos.
     */
    List<Object> buscarUsuariosComMulta();

    /**
     * Busca um usuário específico pelo nome.
     * @param nome Parte do nome do usuário.
     * @return Lista de usuários encontrados.
     */
    List<Object> buscarPorNome(String nome);
}