package model.dao;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ItemJaCadastradoException;
import model.ItemAcervo;
import model.Livro;

/**
 * @author Grupo 2 
 */

/**
 * Repositório em memória para guardar Livros e Revistas.
 */
public class AcervoRepositorio {

    private static List<ItemAcervo> dados = new ArrayList<>();

    /**
     * Salva um novo item no acervo.
     */
    public void cadastrar(ItemAcervo item) throws ItemJaCadastradoException {
        
        // Se for um Livro, verificamos se o ISBN já existe
        if (item instanceof Livro) {
            Livro novoLivro = (Livro) item; // Conversão (Cast) para acessar o getISBN
            
            for (ItemAcervo i : dados) {
                // Verificamos apenas contra outros Livros que já estão na lista
                if (i instanceof Livro) {
                    Livro livroExistente = (Livro) i;
                    if (livroExistente.getISBN().equals(novoLivro.getISBN())) {
                        throw new ItemJaCadastradoException("Erro ao salvar: O ISBN '" + novoLivro.getISBN() + "' já está cadastrado no sistema.");
                    }
                }
            }
        }

        // Se passou pela verificação (ou se é Revista), adiciona na lista
        dados.add(item);
        System.out.println("Item cadastrado com sucesso: " + item.getTitulo());
    }

    /**
     * Busca um item pelo seu ID.
     * Retorna null se não encontrar.
     */
    public ItemAcervo buscarPorId(int id) {
        for (ItemAcervo item : dados) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // Não achou
    }

    /**
     * Remove um item do acervo pelo ID e retorna true se removeu, false se não achou.
     */
    public boolean excluir(int id) {
        ItemAcervo item = buscarPorId(id);
        if (item != null) {
            dados.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Retorna a lista completa para exibir na tabela (JTable)
     */
    public List<ItemAcervo> listarTodos() {
        return dados;
    }
}