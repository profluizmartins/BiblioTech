import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private List<Reserva> bancoDeReservas;
    private int contadorId = 1;

    public void criarReseva(Reserva reserva){
        reserva.setId(contadorId++);
        bancoDeReservas.add(reserva);
    }

    public Reserva buscarPorId(int id){
        for (Reserva r : bancoDeReservas){
            if(r.getId() == id){
                return r;
            }
        }
        return null; // atualizar depois para lançar exceção reserva näo encontrada
    }

    public List<Reserva> buscarPorUsuario(Usuario usuario) {
        List<Reserva> reservasEncontradas = new ArrayList<>(); // guardas as reservas encontradas do usuário
        for(Reserva r : bancoDeReservas){
            if(r.getUsuario().getId() == usuario.getId()){
                reservasEncontradas.add(r);
            }
        }
        return reservasEncontradas; // lançar exceção caso não for encontrada nenhuma resevra
    }

    public List<Reserva> buscarPorLivro(Livro livro){
        List<Reserva> filaDeEspera = new ArrayList<>(); // guarda as reservas feitas para o livro (fila de espera)
        for(Reserva r : bancoDeReservas){
            if(r.getLivro().getId() == livro.getId()){
                filaDeEspera.add(r);
            }
        }
        return filaDeEspera; // lançar exceção caso não for encontrada nenhuma resevra
    }

    public List<Reserva> listarTodas(){
        return new ArrayList<>(bancoDeReservas);
        // lançar exceção caso nao tenha nenhuma reserva no banco
    }

    public void atualizar(Reserva atualizacao){
        Reserva reserva = buscarPorId(atualizacao.getId()); // busca a reserva pelo id para a alteração
        if ( reserva != null){
            reserva.setStatus(atualizacao.getStatus()); // altera a reseva pela atualizaçao
            reserva.setLivro(atualizacao.getLivro());
            reserva.setDataExpiracao(atualizacao.getDataExpiracao());
        }
        // lancar excecao caso nao seja encontrado nenhuma reserva
    };

    public void deletar(int id){
        bancoDeReservas.removeIf(r -> r.getId() == id); // metodo que remove apartir de uma condição, se o id da reserva for igual ao do parametro, então ele a remove
    } // lançar exceção caso não seja econtrado nenhuma reserva
}
