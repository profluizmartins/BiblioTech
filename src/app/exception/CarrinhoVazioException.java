package app.exception;


/**
 * Exceção lançada quando uma operação exige que o carrinho contenha itens,
 * mas ele se encontra vazio.
 */
public class CarrinhoVazioException extends RuntimeException {

    public CarrinhoVazioException() {
        super("O carrinho está vazio.");
    }

    public CarrinhoVazioException(String mensagem) {
        super(mensagem);
    }
}