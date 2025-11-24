package Exceptions;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String cpf) {
        super("CPF '" + cpf + "' inválido. Deve conter 11 dígitos numéricos.");
    }
}