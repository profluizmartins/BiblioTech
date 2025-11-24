package model.excecoes;

public class NenhumResultadoEncontradoException extends Exception {
    /**
     * Emite a excessão quando nenhum resultado é encontrado
     */
    public NenhumResultadoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
