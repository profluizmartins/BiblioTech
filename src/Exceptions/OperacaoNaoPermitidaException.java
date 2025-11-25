package Exceptions;

/**
 * Exceção lançada quando uma operação (como a exclusão)
 * não pode ser realizada devido a pendências no sistema
 */
public class OperacaoNaoPermitidaException extends RuntimeException {
    /** Construtor da exceção.
     * @param operacao O nome da operação que falhou (ex: "Exclusão").
     * @param motivo O motivo da falha (ex: "Usuário possui empréstimos ativos.").
     */
    public OperacaoNaoPermitidaException(String operacao, String motivo) {
        super("Operação de " + operacao + " não permitida. Motivo: " + motivo);
    }
}