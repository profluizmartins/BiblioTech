package app;

// Os dois "imports" abaixo podem ser deletados posteriormente.
import model.Funcionario;
import model.SessaoUsuario;
import View.TelaLogin;

public class Main {
    public static void main(String[] args) {
        /* 

        // Verificação do Singleton.
        SessaoUsuario S1 = SessaoUsuario.getInstance();
        SessaoUsuario S2 = SessaoUsuario.getInstance();

        if (S1 == S2) {
            System.out.println("O Singleton foi implementado corretamente.");
        } else {
            System.out.println("O Singleton não foi implementado corretamente.");
        }

        // Logando um funcionário e recuperando as informações.
        Funcionario f = new Funcionario(
            "César", "00000000000", "Endereço Desconhecido",
            1, "admin", "123", "Administrador"
        );
        S1.logar(f);

        System.out.println("Funcionário logado via S2: " + S2.getFuncionarioLogado().getNome() + ".");
        
        */

        new TelaLogin().setVisible(true);
    }
}

