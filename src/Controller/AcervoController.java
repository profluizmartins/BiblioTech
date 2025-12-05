package Controller;

import java.util.List;
import Exceptions.ItemNaoEncontradoException;
import Exceptions.OperacaoNaoPermitidaException;
import model.ItemAcervo;
import model.dao.AcervoRepositorio;

/**
 * @author Grupo 2 
 */

/**
 * Controlador responsável por gerenciar as ações do Painel de Acervo (Listagem e Exclusão).
 */
public class AcervoController {

    private AcervoRepositorio repositorio;

    public AcervoController() {
       
        this.repositorio = new AcervoRepositorio();
    }

    /**
     * Retorna a lista de todos os itens para preencher a JTable na View.
     */
    public List<ItemAcervo> listarTodos() {
        return repositorio.listarTodos();
    }

    /**
     * Aplica a regra de negócio: Não pode excluir se estiver Emprestado ou Reservado.
     */
    public void excluir(int id) throws ItemNaoEncontradoException, OperacaoNaoPermitidaException {
        // 1. Busca o item para verificar o status
        ItemAcervo item = repositorio.buscarPorId(id);

        // Se não achou, lança erro
        if (item == null) {
            throw new ItemNaoEncontradoException("Erro: O item que você está tentando excluir não foi encontrado.");
        }

        if (item.getStatus().equalsIgnoreCase("Emprestado") || item.getStatus().equalsIgnoreCase("Reservado")) {
            throw new OperacaoNaoPermitidaException("Não é possível excluir um item que está atualmente Emprestado ou Reservado.");
        }

        // Se passou pelas regras, manda o repositório excluir
        repositorio.excluir(id);
    }
}