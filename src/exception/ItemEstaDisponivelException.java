package exception;

// Arquivo agrupando as exceções para facilitar a organização

public class ItemEstaDisponivelException extends RuntimeException {
    public ItemEstaDisponivelException(String message) { super(message); }
}

