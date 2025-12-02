package Exceptions;

/**
 * Exceção lançada quando uma operação (como a exclusão)
 * não pode ser realizada devido a pendências no sistema
 */
public class OperacaoNaoPermitidaException extends RuntimeException {
	 /**
     *  Construtor da exceção.
     */
    public OperacaoNaoPermitidaException(String operacao, String motivo) {
        super("Operação de " + operacao + " não permitida. Motivo: " + motivo);
    }
}