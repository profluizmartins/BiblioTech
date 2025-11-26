package Exception;

public class OperacaoNaoPermitidaException extends Exception {
    public OperacaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}