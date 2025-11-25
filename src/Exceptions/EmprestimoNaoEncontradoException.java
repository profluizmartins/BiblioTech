package Exceptions;

/**
 * Classe de exceção para emprestimo não encontrado, que herda de Exception
 *
 * @author Rômulo Henrique
 * @author Arthur Souza Santos
 * @author Samuel Barbosa
 * @author Eric Felipe
 * @author Thiago Tomaz
 *
 * @version 1.0
 */
public class EmprestimoNaoEncontradoException extends Exception {

    /**
     * Método construtor da classe EmprestimoNaoEncontradoException
     *
     * @param msg Mensagem a ser exibida referente ao tipo de exceção
     *
     */
    public EmprestimoNaoEncontradoException(String msg) {
        super(msg);
    }
}
