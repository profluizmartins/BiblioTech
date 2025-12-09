package model;

import java.util.List;

/**
 * Interface responsável por definir as operações de consulta aos usuários do sistema.
 * Esta interface é fornecida pelo Módulo 3 (Gestão de Usuários) e é consumida pelo
 * Módulo 8 para geração de relatórios e buscas relacionadas a usuários.
 *
 * Assim como em IAcervoRepositorio, o Módulo 8 não implementa esta interface,
 * apenas a utiliza, mantendo o baixo acoplamento entre módulos do sistema.
 */
public interface IUsuarioRepositorio {

    /**
     * Retorna todos os usuários que possuem multas pendentes.
     * Este método é utilizado para a geração do relatório de inadimplência do sistema.
     *
     * @return Lista contendo usuários com multas registradas ou status suspenso.
     */
    List<Object> buscarUsuariosComMulta();

    /**
     * Busca usuários a partir de seu nome ou parte dele.
     * Utilizado nas funcionalidades de busca por usuário, quando implementadas.
     *
     * @param nome Texto utilizado como critério de filtragem.
     * @return Lista de usuários encontrados com o nome informado.
     */
    List<Object> buscarPorNome(String nome);
}