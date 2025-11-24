package Exceptions;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String cpf) {
        super("Erro ao salvar: O CPF '" + cpf + "' já pertence a outro usuário.");
    }
}