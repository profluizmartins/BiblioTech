package app.model;

import java.time.LocalDate;
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
    public void criarReserva(Reserva reserva){
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
        return reservasEncontradas; // lançar exceção caso não for encontrada nenhuma reserva
    }

    /**
     * Método para buscar reservas feitas para um item do Acervo
     * @param itemAcervo item na qual será feita a busca
     * @return lista de reservas encontradas
     */
    public List<Reserva> buscarPorItem(ItemAcervo itemAcervo){
        List<Reserva> filaDeEspera = new ArrayList<>(); // guarda as reservas feitas para o item (fila de espera)
        for(Reserva r : bancoDeReservas){
            if(r.getLivro().getId() == itemAcervo.getId()){
                filaDeEspera.add(r);
            }
        }
        return filaDeEspera; // lançar exceção caso não for encontrada nenhuma reserva
    }

    /**
     * Método que retorna todas as reservas do banco de dados
     * @return lista com todas as reservas do banco de dados
     */
    public List<Reserva> listarTodas(){
        return new ArrayList<>(bancoDeReservas);
        // lançar exceção caso não tenha nenhuma reserva no banco
    }

    /**
     * Método para atualizar informações de uma reserva no banco de dados
     * @param reserva reserva a ser atualizada
     * @param newStatus novo status
     * @param newItemAcervo novo item
     * @param newDataExpiracao nova data de expiração
     */
    public void atualizar(Reserva reserva, int newStatus, ItemAcervo newItemAcervo, LocalDate newDataExpiracao){
        if ( reserva != null){
            reserva.setStatus(newStatus);
            reserva.setLivro(newItemAcervo);
            reserva.setDataExpiracao(newDataExpiracao);
        }
        // lançar exceção caso não seja encontrado nenhuma reserva
    };

    /**
     * Método para remover uma reserva no banco de dados
     * @param id ID da reserva a ser removida
     */
    public void deletar(int id){
        bancoDeReservas.removeIf(r -> r.getId() == id); // método que remove a partir de uma condição, se o id da reserva for igual ao do parâmetro, então ele a remove
    } // lançar exceção caso não seja encontrado nenhuma reserva
}
