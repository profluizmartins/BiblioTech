package Exceptions;

/**
 * @author Grupo 2 
 */

public class ISBNInvalidoException extends Exception {
    public ISBNInvalidoException(String mensagem) {
        super(mensagem);
    }
}