package model;

/** 
 * Classe responsável por manter a sessão de login.
 * A classe implementa o padrão Singleton.
*/

public class SessaoUsuario {

    private static SessaoUsuario instance;

    private Funcionario funcionarioLogado;

    private SessaoUsuario() {
    }

    /**
     * Retorna a instância da sessão do usuário. Caso não exista instância, ela será criada.
     * 
     * @return Instância única de {@code SessaoUsuario}.
     */

    public static SessaoUsuario getInstance() {
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
     * Retorna o funcionário atualmente "logado".
     * 
     * @return Funcionário "logado" ou {@code null}, caso não haja funcionário "logado".
    */

    public Funcionario getFuncionario() {
        return funcionarioLogado;
    }
}