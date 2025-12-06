package app.exception;


public class EmprestimoException extends RuntimeException {
    public EmprestimoException(String mensagem) {
        super(mensagem);
    }
}