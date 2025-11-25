package app.modell;

import java.time.LocalDate;

/**
 * Classe MODELL de reservas
 * @author Ana Elisa
 * @author Davi Porto
 * @author Gustavo Ribeiro
 * @author Maikow
 * @author Marllus Coutinho
 *
 * @version 1.0
 * @since 25-11-2025
 */
public class Reserva {
    private int id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataReserva;
    private LocalDate dataExpiracao;
    private int status;

    private static final int PENDENTE = 1;
    private static final int DISPONIVEL = 2;
    private static final int CONCLUIDA = 3;
    private static final int CANCELADA = 4;
    private static final int EXPIRADA = 5;

    public Reserva(int id, Usuario usuario, Livro livro) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = LocalDate.now();
        this.dataExpiracao = null;
        this.status = PENDENTE;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }
    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
