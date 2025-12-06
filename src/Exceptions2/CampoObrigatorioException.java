package Exceptions2;

public class CampoObrigatorioException extends Exception {
    public CampoObrigatorioException(String mensagem) {
        super(mensagem);
    }
}