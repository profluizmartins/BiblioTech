package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaRepositorio {
    private List<Reserva> reservas = new ArrayList<>();
    private int contadorId = 1;

    public void criarReserva(Reserva reserva) {
        // Simula auto-incremento de banco de dados
        // Usa reflexão ou método setter se disponível, aqui recriamos com ID correto ou assumimos setter
        // Como o construtor pede ID, idealmente o ID é gerado antes ou setado via setter.
        // Vamos assumir que o objeto passado já tem ID ou ajustamos aqui:
        // Como o campo é private e não tem setter no exemplo anterior, ajustarei via construtor no Controller.
        reservas.add(reserva);
    }

    public int proximoId() { return contadorId++; }

    public Reserva buscarPorId(int id) {
        return reservas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Reserva> buscarPorUsuario(Usuario usuario) {
        return reservas.stream()
                .filter(r -> r.getUsuario().getId() == usuario.getId())
                .collect(Collectors.toList());
    }

    public List<Reserva> buscarPorItem(ItemAcervo item) {
        return reservas.stream()
                .filter(r -> r.getItemAcervo().getId() == item.getId())
                .collect(Collectors.toList());
    }

    public List<Reserva> listarTodas() {
        return new ArrayList<>(reservas);
    }

    public void atualizarStatus(Reserva reserva, String status) {
        reserva.setStatus(status);
    }

    public void remover(int id) {
        reservas.removeIf(r -> r.getId() == id);
    }
}