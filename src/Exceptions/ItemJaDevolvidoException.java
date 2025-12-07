package Exceptions;

/**
 * Classe de exceção para um item já devolvido, que herda de Exception
 *
 * @author Rômulo Henrique
 * @author Arthur Souza Santos
 * @author Samuel Barbosa
 * @author Eric Felipe
 * @author Thiago Tomaz
 *
 * @version 1.0
 */
public class ItemJaDevolvidoException extends Exception {
    /**
     * Método construtor da classe ItemJaDevolvidoException
     *
     * @param msg Mensagem a ser exibida referente ao tipo de exceção
     *
     */
    public ItemJaDevolvidoException(String msg) {
        super(msg);
    }
}