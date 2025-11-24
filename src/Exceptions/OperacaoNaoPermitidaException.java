package Exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException(String operacao, String motivo) {
        super("Operação de " + operacao + " não permitida. Motivo: " + motivo);
    }
}