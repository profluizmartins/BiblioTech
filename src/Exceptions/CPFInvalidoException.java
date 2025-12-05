package Exceptions;

/**
 * Exceção lançada quando o CPF fornecido não possui o formato correto
 * (deve conter 11 dígitos numéricos).
 */
public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String cpf) {
        /**
        * Construtor da exceção.
         * @param cpf O CPF que causou o erro.
        */
        super("CPF '" + cpf + "' inválido. Deve conter 11 dígitos numéricos.");
    }
}