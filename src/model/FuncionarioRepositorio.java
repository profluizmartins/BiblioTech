package model;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositorio{

    private static List<Funcionario> funcionarios = new ArrayList<>();

    static {
        // Usuário fictício para testes.
        funcionarios.add(new Funcionario(
            "César",
            "00000000000",
            "Endereço Desconhecido",
            1,
            "admin",
            "123",
            "Administrador"
        ));
    }

    /**
     * Método que busca funcionários por login.
     * 
     * @param login
     * @return
     */

    public Funcionario buscarPorLogin(String login) {
        for (Funcionario f : funcionarios) {
            if (f.getLogin().equalsIgnoreCase(login)) {
                return f;
            }
        }
        return null;
    }
}
