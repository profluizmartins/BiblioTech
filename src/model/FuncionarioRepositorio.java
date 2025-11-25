package model;

import java.util.ArrayList;

/**
 * DAO responsável por armazenar, recuperar e remover funcionários.
 * Implementado pelo Grupo 9.
 */
public class FuncionarioRepositorio {

    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static int proximoId = 1;

    /**
     * Adiciona um funcionário ao repositório, atribuindo ID automaticamente.
     */
    public static void adicionar(Funcionario f) {
        f.setId(proximoId);
        proximoId++;
        funcionarios.add(f);
    }

    /**
     * Lista todos os funcionários cadastrados.
     */
    public static ArrayList<Funcionario> listar() {
        return funcionarios;
    }

    /**
     * Busca um funcionário pelo login.
     *
     * @param login login a ser procurado
     * @return funcionário encontrado ou null
     */
    public static Funcionario buscarPorLogin(String login) {
        for (Funcionario f : funcionarios) {
            if (f.getLogin().equals(login)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Remove um funcionário do repositório.
     */
    public static void remover(Funcionario f) {
        funcionarios.remove(f);
    }
}