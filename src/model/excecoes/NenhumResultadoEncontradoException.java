package model.excecoes;

/**
 * Exceção lançada quando uma operação de busca é realizada corretamente,
 * porém não existem registros compatíveis com os critérios informados.
 *
 * Esta exceção é parte essencial do Módulo 8, pois permite que a camada
 * View apresente mensagens informativas ao usuário quando não há resultados,
 * evitando comportamentos silenciosos ou ambiguidades na interface.
 */
public class NenhumResultadoEncontradoException extends Exception {

    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem explicativa detalhando a ausência de resultados
     *                 na operação solicitada.
     */
    public NenhumResultadoEncontradoException(String mensagem) {
        super(mensagem);
    }
}