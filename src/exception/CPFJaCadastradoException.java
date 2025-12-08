package exception;

public class CPFJaCadastradoException extends ValidacaoException {
    
    public CPFJaCadastradoException() {
    	
        super("Erro ao salvar: O CPF informado já pertence a outro usuário/funcionário.");
    }
}