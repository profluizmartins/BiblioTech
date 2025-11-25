package app.modell;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO de Reservas
 */
public class ReservaDAO {
    private List<Reserva> bancoDeReservas;
    private int contadorId = 1;

    /**
     * Método para criar uma reserva no banco de dados
     * @param reserva reserva a ser criada
     */
    public void criarReseva(Reserva reserva){
        reserva.setId(contadorId++);
        bancoDeReservas.add(reserva);
    }

    /**
     * Método para buscar uma reserva pelo ID no banco de dados
     * @param id ID da reserva a ser encontrada
     * @return reserva encontrada
     */
    public Reserva buscarPorId(int id){
        for (Reserva r : bancoDeReservas){
            if(r.getId() == id){
                return r;
            }
        }
        return null; // atualizar depois para lançar exceção reserva näo encontrada
    }

    /**
     * Método para buscar reservas feitas por um usuário
     * @param usuario usuário na qual será feita a busca
     * @return lista de reservas encontradas
     */
    public List<Reserva> buscarPorUsuario(Usuario usuario) {
        List<Reserva> reservasEncontradas = new ArrayList<>(); // guardas as reservas encontradas do usuário
        for(Reserva r : bancoDeReservas){
            if(r.getUsuario().getId() == usuario.getId()){
                reservasEncontradas.add(r);
            }
        }
        return reservasEncontradas; // lançar exceção caso não for encontrada nenhuma resevra
    }

    /**
     * Método para buscar reservas feitas para um livro
     * @param livro livro na qual será feita a busca
     * @return lista de reservas encontradas
     */
    public List<Reserva> buscarPorLivro(Livro livro){
        List<Reserva> filaDeEspera = new ArrayList<>(); // guarda as reservas feitas para o livro (fila de espera)
        for(Reserva r : bancoDeReservas){
            if(r.getLivro().getId() == livro.getId()){
                filaDeEspera.add(r);
            }
        }
        return filaDeEspera; // lançar exceção caso não for encontrada nenhuma resevra
    }

    /**
     * Método que retorna todas as reservas do banco de dados
     * @return lista com todas as reservas do banco de dados
     */
    public List<Reserva> listarTodas(){
        return new ArrayList<>(bancoDeReservas);
        // lançar exceção caso nao tenha nenhuma reserva no banco
    }

    /**
     * Método para atualizar informações de uma reserva no banco de dados
     * @param atualizacao reserva a ser atualizada
     */
    public void atualizar(Reserva atualizacao){
        Reserva reserva = buscarPorId(atualizacao.getId()); // busca a reserva pelo id para a alteração
        if ( reserva != null){
            reserva.setStatus(atualizacao.getStatus()); // altera a reseva pela atualizaçao
            reserva.setLivro(atualizacao.getLivro());
            reserva.setDataExpiracao(atualizacao.getDataExpiracao());
        }
        // lancar excecao caso nao seja encontrado nenhuma reserva
    };

    /**
     * Método para remover uma reserva no banco de dados
     * @param id ID da reserva a ser removida
     */
    public void deletar(int id){
        bancoDeReservas.removeIf(r -> r.getId() == id); // metodo que remove apartir de uma condição, se o id da reserva for igual ao do parametro, então ele a remove
    } // lançar exceção caso não seja econtrado nenhuma reserva
}

    public void deletar(int id){
        bancoDeReservas.removeIf(r -> r.getId() == id); // metodo que remove apartir de uma condição, se o id da reserva for igual ao do parametro, então ele a remove
    } // lançar exceção caso não seja econtrado nenhuma reserva
}
