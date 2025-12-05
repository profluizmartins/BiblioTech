package Exceptions;
/**
 * Exceção lançada quando um campo de formulário obrigatório, 
 * especificamente o campo Nome, não é preenchido ou não atende ao tamanho mínimo (3 caracteres).
 * Esta exceção usa uma mensagem de erro fixa.
 */
public class CampoObrigatorioException extends RuntimeException {
	 /**
     * Construtor padrão da exceção CampoObrigatorioException.
     * Não recebe parâmetros e utiliza uma mensagem de erro fixa que referencia o campo 'Nome'.
     */
    public CampoObrigatorioException(String campo) {
        super("O campo '" + campo + "' é obrigatório (mínimo 3 caracteres).");
    }
}