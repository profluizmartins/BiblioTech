package Exceptions;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensagem) {
        super("Data de Nascimento inválida");
    }
}