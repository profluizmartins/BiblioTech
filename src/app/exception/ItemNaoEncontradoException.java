package app.exception;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String msg) {
        super(msg);
    }
}