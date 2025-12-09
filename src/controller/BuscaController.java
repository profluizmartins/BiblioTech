package controller;

import java.util.List;
import model.IAcervoRepositorio;
import model.IUsuarioRepositorio;
import model.excecoes.DadosInsuficientesException;
import model.excecoes.NenhumResultadoEncontradoException;

/**
 * Controller responsável pelas regras de negócio relacionadas às
 * operações de busca no acervo e nos usuários do sistema.
 *
 * Esta classe faz parte da camada Model-Controller do padrão arquitetural MVC.
 */
public class BuscaController {

    /** Repositório de itens do acervo, definido e implementado no Módulo 2. */
    private IAcervoRepositorio acervoRepo;

    /** Repositório de usuários, definido e implementado no Módulo 3. */
    private IUsuarioRepositorio usuarioRepo;

    /**
     * Construtor que recebe as dependências necessárias para execução das buscas.
     *
     * @param acervoRepo Implementação concreta da interface IAcervoRepositorio.
     * @param usuarioRepo Implementação concreta da interface IUsuarioRepositorio.
     */
    public BuscaController(IAcervoRepositorio acervoRepo, IUsuarioRepositorio usuarioRepo) {
        this.acervoRepo = acervoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Realiza uma busca por itens do acervo cujo título contenha o termo informado.
     *
     * @param titulo Parte ou totalidade do título buscado.
     * @return Lista de objetos representando os resultados encontrados.
     *
     * @throws DadosInsuficientesException Quando o campo está vazio ou nulo.
     * @throws NenhumResultadoEncontradoException Quando a busca é válida, mas sem retorno.
     */
    public List<Object> buscarPorTitulo(String titulo)
            throws DadosInsuficientesException, NenhumResultadoEncontradoException {

        // Validação mínima da entrada
        if (titulo == null || titulo.isBlank()) {
            throw new DadosInsuficientesException("O campo 'Título' deve ser preenchido.");
        }

        List<Object> resultado = acervoRepo.buscarPorTitulo(titulo);

        // Tratamento da ausência de resultados
        if (resultado.isEmpty()) {
            throw new NenhumResultadoEncontradoException("Nenhum item encontrado com o título informado.");
        }

        return resultado;
    }

    /**
     * Realiza busca no acervo com base no nome do autor.
     *
     * @param autor Nome completo ou parcial do autor.
     * @return Lista de objetos com publicações associadas ao autor.
     *
     * @throws DadosInsuficientesException Se o critério informado estiver vazio.
     * @throws NenhumResultadoEncontradoException Se não houver correspondências.
     */
    public List<Object> buscarPorAutor(String autor)
            throws DadosInsuficientesException, NenhumResultadoEncontradoException {

        if (autor == null || autor.isBlank()) {
            throw new DadosInsuficientesException("O campo 'Autor' deve ser preenchido.");
        }

        List<Object> resultado = acervoRepo.buscarPorAutor(autor);

        if (resultado.isEmpty()) {
            throw new NenhumResultadoEncontradoException("Nenhum item encontrado para o autor informado.");
        }

        return resultado;
    }

    /**
     * Consulta itens publicados em um ano específico.
     *
     * @param ano Valor numérico representando o ano de publicação.
     * @return Lista de obras catalogadas no ano informado.
     *
     * @throws NenhumResultadoEncontradoException Se a busca retornar vazia.
     */
    public List<Object> buscarPorAno(int ano)
            throws NenhumResultadoEncontradoException {

        List<Object> resultado = acervoRepo.buscarPorAno(ano);

        if (resultado.isEmpty()) {
            throw new NenhumResultadoEncontradoException("Nenhum item encontrado para o ano informado.");
        }

        return resultado;
    }

    /**
     * Busca usuários cujo nome contenha o termo informado.
     *
     * @param nome Nome completo ou parcial do usuário desejado.
     * @return Lista contendo usuários compatíveis com o critério de busca.
     *
     * @throws DadosInsuficientesException Em caso de entrada vazia.
     * @throws NenhumResultadoEncontradoException Se nenhum usuário for localizado.
     */
    public List<Object> buscarUsuarioPorNome(String nome)
            throws DadosInsuficientesException, NenhumResultadoEncontradoException {

        if (nome == null || nome.isBlank()) {
            throw new DadosInsuficientesException("O campo 'Nome' deve ser preenchido.");
        }

        List<Object> resultado = usuarioRepo.buscarPorNome(nome);

        if (resultado.isEmpty()) {
            throw new NenhumResultadoEncontradoException("Nenhum usuário encontrado com o nome informado.");
        }

        return resultado;
    }

    // MODIFICAÇÃO: Método adicionado para permitir listar tudo quando os campos estão vazios
    /**
     * Retorna todos os itens do acervo sem aplicar filtros.
     * Utilizado quando o usuário clica em "Pesquisar" sem preencher critérios.
     * * @return Lista completa do acervo.
     */
    public List<Object> listarAcervoCompleto() {
        // Chama o método do repositório que retorna a lista completa (definido na interface)
        return acervoRepo.listarTodos();
    }
}