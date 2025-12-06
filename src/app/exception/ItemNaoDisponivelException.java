package app.exception;

public class ItemNaoDisponivelException extends RuntimeException {
    public ItemNaoDisponivelException(String msg) {
        super(msg);
    }
}