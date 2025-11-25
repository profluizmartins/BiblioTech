package Exceptions;

/**
 * Exceção lançada quando uma operação (como a exclusão)
 * não pode ser realizada devido a pendências no sistema
 */
public class OperacaoNaoPermitidaException extends RuntimeException {
    /**
     *  Construtor da exceção.
     */
    public OperacaoNaoPermitidaException() {
        super("Não é possível excluir um usuário que possui empréstimos ativos ou multas pendentes.");
    }
}