package exceptions;

/**
 * Exceção lançada quando uma multa não é encontrada pelo ID informado.
 * Já formata a mensagem de erro
 */
public class MultaNaoEncontradaException extends Exception {

    /**
     * ID de serialização padrão para evitar avisos do Java.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construtor inteligente: Recebe apenas o ID e monta a frase padrão do trabalho.
     * Ex: "Pagamento falhou: Nenhuma multa pendente encontrada com o ID '10'."
     *
     * @param idMulta identificador da multa que não foi achada.
     */
    public MultaNaoEncontradaException(int idMulta) {
        super("Pagamento falhou: Nenhuma multa pendente encontrada com o ID '" + idMulta + "'.");
    }

    /**
     * Construtor genérico caso precise passar uma mensagem diferente.
     *
     * @param message mensagem descritiva do erro.
     */
    public MultaNaoEncontradaException(String message) {
        super(message);
    }
}