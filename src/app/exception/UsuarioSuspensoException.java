package app.exception;

public class UsuarioSuspensoException extends RuntimeException {
    public UsuarioSuspensoException(String msg) {
        super(msg);
    }
}