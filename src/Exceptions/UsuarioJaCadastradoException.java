package Exceptions;

/**
 * Exceção lançada quando se tenta salvar um novo usuário
 * com um CPF que já existe no sistema (regra de unicidade).
 */
public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String cpf) {
    	/**
         * Construtor da exceção.
        * @param cpf O CPF duplicado que causou o erro.
        */
        super("Erro ao salvar: O CPF '" + cpf + "' já pertence a outro usuário.");
    }
}