package Exceptions;

/**
 * Exceção lançada quando a idade calculada do usuário
 * é menor que o mínimo permitido (10 anos, conforme especificação).
 */
public class MenorDeIdadeException extends RuntimeException {
    public MenorDeIdadeException(int idade) {
        /**
        * Construtor da exceção.
        * @param idade A idade calculada do usuário.
        */
        super("O usuário deve ter pelo menos 10 anos para se cadastrar.");
    }
}