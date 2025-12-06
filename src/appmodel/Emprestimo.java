package appmodel;

import java.time.LocalDate;
import Model2.Usuario;

/**
 * Representa um empréstimo realizado na biblioteca.
 * 
 * A classe contém todas as informações necessárias para registrar um processo 
 * de empréstimo, incluindo dados do usuário, do item emprestado, datas e o 
 * status atual.
 * 
 * Cada empréstimo possui:
 * Um identificador único (id)
 * O usuário que realizou o empréstimo
 * O item do acervo que foi emprestado
 * A data do empréstimo
 * A data prevista para devolução
 * O status do empréstimo (ativo, atrasado, devolvido, etc.)
 * 
 * Esta classe é utilizada pelo sistema para controlar o fluxo de empréstimos
 * e facilitar operações como renovação ou devolução.
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
     * 
     * @param id                       Identificador único do empréstimo.
     * @param usuario                  Usuário que está realizando o empréstimo.
     * @param item                     Item do acervo que está sendo emprestado.
     * @param dataEmprestimo           Data em que o empréstimo foi realizado.
     * @param dataPrevistaDevolucao    Data limite para devolução do item.
     * @param status                   Estado atual do empréstimo.
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

    /**
     * Obtém o identificador do empréstimo.
     * @return ID do empréstimo.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém o usuário associado ao empréstimo.
     * @return Usuário do empréstimo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Obtém o item emprestado.
     * @return Item do acervo emprestado.
     */
    public ItemAcervo getItem() {
        return item;
    }

    /**
     * Obtém a data em que o empréstimo foi realizado.
     * @return Data do empréstimo.
     */
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * Obtém a data prevista para devolução do item.
     * @return Data prevista de devolução.
     */
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    /**
     * Obtém o status atual do empréstimo.
     * @return Status do empréstimo.
     */
    public StatusEmprestimo getStatus() {
        return status;
    }

    /**
     * Atualiza o status do empréstimo.
     * 
     * @param status Novo status para o empréstimo.
     */
    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }
}
