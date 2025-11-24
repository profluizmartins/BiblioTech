package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorio {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static int proximaMatricula = 1000; 

    public Usuario salvar(Usuario usuario) {
        if (usuario.getMatricula() == null || usuario.getMatricula().isEmpty()) {
            String novaMatricula = "M" + proximaMatricula++;
            usuario.setMatricula(novaMatricula);
        }
        
        usuarios.add(usuario);
        return usuario;
    }

    public Optional<Usuario> buscarPorCpf(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
    
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios); 
    }

    public Usuario atualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf().equals(usuario.getCpf())) {
                usuarios.set(i, usuario); 
                return usuario;
            }
        }
        return null;
    }

    public boolean excluir(String cpf) {
        Optional<Usuario> usuarioParaExcluir = buscarPorCpf(cpf);
        
        if (usuarioParaExcluir.isPresent()) {
            return usuarios.remove(usuarioParaExcluir.get());
        }
        return false; 
    }
}
