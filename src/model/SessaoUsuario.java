package model;

/** 
 * Classe responsável por manter a sessão de login.
 * 
 * A classe implementa o padrão Singleton.
*/

public class SessaoUsuario {

    private static SessaoUsuario instance;

    private Funcionario funcionarioLogado;

    private SessaoUsuario() {
    }

    /**
     * Retorna a instância da sessão do usuário. Caso não exista instância, ela será criada.
     * Adicionado 'synchronized' para evitar problemas se múltiplos processos tentarem acessar ao mesmo tempo.
     * @return Instância única de {@code SessaoUsuario}.
     */

    public static synchronized SessaoUsuario getInstance() {
        if (instance == null) {
            instance = new SessaoUsuario();
        }
        return instance;
    }

    /**
     * Registra o funcionário "logado" no sistema.
     * 
     * @param f Funcionário "logado".
     */

    public void logar(Funcionario f) {
        this.funcionarioLogado = f;
    }
    /**
     * Remove o usuário da sessão.
     * 
     */
    public void logout() {
        this.funcionarioLogado = null;
    }
    /**
     * Retorna o funcionário atualmente "logado".
     * 
     * @return Funcionário "logado" ou {@code null}, caso não haja funcionário "logado".
    */

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
}
