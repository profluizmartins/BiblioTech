package exception;

public class LoginJaExisteException extends ValidacaoException {
    
    public LoginJaExisteException(String login) {
        
        super("Erro ao salvar: O login '" + login + "' já está sendo utilizado por outro funcionário.");
    }
}