package Exceptions;

/**
 * Exceção lançada especificamente para indicar que a Data de Nascimento
 * fornecida está em um formato inválido ou não pode ser processada.
 * Esta exceção usa uma mensagem de erro fixa.
 */
public class ValidacaoException extends RuntimeException {
    
    /**
     * Construtor padrão da exceção ValidacaoException.
     * Não recebe parâmetros e utiliza uma mensagem de erro fixa que referencia a "Data de Nascimento inválida".
     */
    public ValidacaoException() {
        super("Data de Nascimento inválida.");
    }
}