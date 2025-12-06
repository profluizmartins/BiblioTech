package app.exception;


/**
 * Exceção lançada quando um item inválido é fornecido ou não é encontrado.
 */
public class ItemInvalidoException extends RuntimeException {

    public ItemInvalidoException() {
        super("O item fornecido é inválido.");
    }

    public ItemInvalidoException(String mensagem) {
        super(mensagem);
    }
}