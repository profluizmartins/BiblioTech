package Exceptions;

public class MenorDeIdadeException extends RuntimeException {
    public MenorDeIdadeException(int idade) {
        super("O usuário deve ter pelo menos 10 anos para se cadastrar.");
    }
}