package exceptions;

/**
 * Exceção lançada quando se tenta pagar uma multa que já consta como PAGA.
 */
public class MultaJaPagaException extends Exception {

    /**
     * ID de serialização padrão para evitar avisos do Java.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construtor padrão: Já define a mensagem exigida no PDF.
     * "Pagamento falhou: Esta multa já foi paga anteriormente."
     */
    public MultaJaPagaException() {
        super("Pagamento falhou: Esta multa já foi paga anteriormente.");
    }

    /**
     * Construtor genérico para mensagens personalizadas.
     *
     * @param message mensagem descritiva do erro.
     */
    public MultaJaPagaException(String message) {
        super(message);
    }
}