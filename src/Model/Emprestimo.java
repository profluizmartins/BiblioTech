package Model;
import Model.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que contém dados do empréstimo do item
 * (Versão ajustada com correções de sintaxe para o Grupo 5)
 */
public class Emprestimo {
    private int acervoId;
    private Usuario usuario;
    private String itemEmprestado;
    private boolean statusDevolvido; // false = Ativo, true = Finalizado
    private boolean statusAtrasado;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;


    // Data PREVISTA para devolução
    // Construtor
    public Emprestimo(ItemAcervo item, Usuario usuario, String itemEmprestado,
                      boolean statusDevolvido, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.acervoId = item.getId();
        this.usuario = usuario;
        this.itemEmprestado = itemEmprestado;
        this.statusDevolvido = statusDevolvido;
        this.statusAtrasado = statusAtrasado;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }


    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    
    public boolean getStatusDevolvido() { return statusDevolvido; }
    public void setStatusDevolvido(boolean statusDevolvido) { this.statusDevolvido = statusDevolvido; }

    public boolean getStatusAtrasado() { return statusAtrasado; }
    public void setStatusAtrasado(boolean statusAtrasado) { this.statusAtrasado = statusAtrasado; }

    public String getItemEmprestado() { return itemEmprestado; }
    public void setItemEmprestado(String itemEmprestado) { this.itemEmprestado = itemEmprestado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public int getIdItem() { return acervoId; }
    public void setIdItem(int idItem) { this.acervoId = idItem; }
}

