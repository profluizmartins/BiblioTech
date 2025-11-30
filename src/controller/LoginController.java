package controller;

import exception.AutenticacaoFalhouException;
import model.SessaoUsuario;
import model.Funcionario;
import model.FuncionarioRepositorio;

/**
 * Controlador responsável pelo processo de autenticação de usuários.
 *
 * <p>Esta classe coordena a comunicação entre a interface de login e o {@link FuncionarioRepositorio}. 
 * Caso a autenticação seja bem-sucedida, o usuário é registrado na sessão por meio da classe {@link SessaoUsuario}.</p>
 */

public class LoginController {

    private FuncionarioRepositorio repositorio;

    public LoginController() {
        this.repositorio = new FuncionarioRepositorio();
    }

    /**
     * Método de autenticação de usuários.
     * 
     * @param login
     * @param senha
     * @throws AutenticacaoFalhouException
     */

    public void autenticar(String login, String senha) throws AutenticacaoFalhouException {

        Funcionario f = repositorio.buscarPorLogin(login);

        if (f == null) {
            throw new AutenticacaoFalhouException("Usuário ou senha inválidos.");
        }

        if (!f.getSenhaHash().equals(senha)) {
            throw new AutenticacaoFalhouException("Usuário ou senha inválidos.");
        }

        SessaoUsuario.getInstance().logar(f);
    }
}