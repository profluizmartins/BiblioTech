
package model_temp;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Repositório (DAO - Data Access Object) para a entidade Usuario.
 * Gerencia o armazenamento em memória, a geração de ID (Pessoa) e de Matrícula (Usuario).
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Henrique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class UsuarioRepositorio {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static int proximoId = 1;
    private static int proximaMatricula = 1000; 

    /**
     * Salva um novo objeto Usuario no repositório.
     * Gera ID (Pessoa) e Matrícula (Usuário) se ele for novo.
     * @param usuario O objeto Usuario a ser salvo.
     * @return O objeto Usuario salvo e com os IDs preenchidos.
     */
    public Usuario salvar(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(proximoId++);
        }
        
        if (usuario.getMatricula() == null || usuario.getMatricula().isEmpty()) {
            String novaMatricula = "M" + proximaMatricula++;
            usuario.setMatricula(novaMatricula);
        }
        
        usuarios.add(usuario);
        return usuario;
    }

    /**
     * Busca um usuário pelo seu CPF no repositório.
     * @param cpf O CPF do usuário a ser buscado.
     * @return O objeto Usuario se ele for encontrado, ou null se não for encontrado.
     */
    public Usuario buscarPorCpf(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                return u;
            }
        }
        return null;
    }
    
    /**
     * Retorna uma lista com todos os usuários cadastrados.
     * @return Uma List de objetos Usuario.
     */
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios); 
    }

    /**
     * Atualiza os dados de um usuário existente, identificado pelo CPF.
     * @param usuario O objeto Usuario com os novos dados.
     * @return O objeto Usuario atualizado se encontrado, ou **null** caso contrário.
     */
    public Usuario atualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf().equals(usuario.getCpf())) {
                usuarios.set(i, usuario);
                return usuario;
            }
        }
        return null; 
    }

    /**
     * Exclui um usuário do repositório usando o CPF.
     * @param cpf O CPF do usuário a ser excluído.
     * @return true se o usuário foi encontrado e removido, false caso contrário.
     */
    public boolean excluir(String cpf) {
        Usuario usuarioParaExcluir = buscarPorCpf(cpf); 
        
        if (usuarioParaExcluir != null) {
            return usuarios.remove(usuarioParaExcluir);
        }
        return false; 
    }
}
