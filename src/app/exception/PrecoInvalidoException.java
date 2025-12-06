package app.exception;

/**
 * Exceção lançada quando um preço negativo ou inconsistente é informado.
 */
public class PrecoInvalidoException extends RuntimeException {

    public PrecoInvalidoException() {
        super("O preço informado é inválido.");
    }

    public PrecoInvalidoException(String mensagem) {
        super(mensagem);
  
   }
}
