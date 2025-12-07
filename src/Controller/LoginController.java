package Controller;

import Exceptions.AutenticacaoFalhouException;
import model.SessaoUsuario;
import View.TelaPrincipalView;
import model.Funcionario;
import model.FuncionarioRepositorio;
import java.awt.Color;
import core.ModuloTeste;

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

        // Validação unificada para não revelar se o erro é login ou senha
        if (f == null || !f.getSenhaHash().equals(senha)) {
            throw new AutenticacaoFalhouException("Erro de Login: Usuário ou senha inválidos. Tente novamente."); 
        }

        SessaoUsuario.getInstance().logar(f);
        //Cria a View Principal
    TelaPrincipalView telaPrincipal = new TelaPrincipalView();
    telaPrincipal.atualizarStatus(f.getNome(), f.getCargo());
    
    //Cria o Controller do Menu
        MenuController menuCtrl = new MenuController(telaPrincipal);
    
        //Carregando módulos falsos para testar a navegação
        try {
            telaPrincipal.adicionarPainelModulo(new ModuloTeste("Acervo", Color.CYAN), "MODULO_ACERVO");
            telaPrincipal.adicionarPainelModulo(new ModuloTeste("Usuario", Color.PINK), "MODULO_USUARIO");
        } catch (Exception e) {
            System.out.println("Erro ao carregar módulos de teste: " + e.getMessage());
        }
    
        //Exibe a tela
        telaPrincipal.setVisible(true);
    }
}
