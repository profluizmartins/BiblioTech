package app.model;

import java.time.LocalDate;

/**
 * Classe que representa um empréstimo realizado na biblioteca.
 * Contém todas as informações necessárias sobre o processo de empréstimo.
 * 
 * @author Grupo 4
 * @version 1.0
 */
public class Emprestimo {

    private int id;
    private Usuario usuario;
    private ItemAcervo item;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private StatusEmprestimo status;

    /**
     * Construtor completo do empréstimo.
     */
    public Emprestimo(int id, Usuario usuario, ItemAcervo item, LocalDate dataEmprestimo,
                      LocalDate dataPrevistaDevolucao, StatusEmprestimo status) {
        this.id = id;
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ItemAcervo getItem() {
        return item;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }
}