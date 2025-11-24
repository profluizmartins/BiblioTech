package model.excecoes;

public class DadosInsuficientesException extends Exception {
    /**
     * Emite a excessão quando os dados são insuficientes.
     */
    public DadosInsuficientesException(String mensagem) {
        super(mensagem);
    }
}
