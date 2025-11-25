package app.model;

import java.time.LocalDate;

/**
 * Classe MODEL de reservas
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
    private ItemAcervo itemAcervo; // atualizar para itemAcervo
    private LocalDate dataReserva;
    private LocalDate dataExpiracao;
    private int status;

    // constantes de status (para facilitar o entendimento do código)
    private static final int PENDENTE = 1; // esperando o item estar disponível para empréstimo
    private static final int DISPONIVEL = 2; // item pronto para empréstimo
    private static final int CONCLUIDA = 3; // ja foi feito o empréstimo
    private static final int CANCELADA = 4; // reserva cancelada pelo usuário ou pela administração
    private static final int EXPIRADA = 5; // usuário não fez a devolução dentro do prazo

    /**
     * Método construtor de Reserva
     * @param id ID do livro
     * @param usuario usuário que fez a reserva
     * @param itemAcervo item reservado
     */
    public Reserva(int id, Usuario usuario, ItemAcervo itemAcervo) {
        this.id = id;
        this.usuario = usuario;
        this.itemAcervo = itemAcervo;
        this.dataReserva = LocalDate.now();
        this.dataExpiracao = null; // criada apenas depois do status DISPONÍVEL
        this.status = PENDENTE; // Inicia com status PENDENTE
    }

    // Métodos getters e setters
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

    public ItemAcervo getLivro() {
        return itemAcervo;
    }
    public void setLivro(ItemAcervo itemAcervo) {
        this.itemAcervo = itemAcervo;
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

