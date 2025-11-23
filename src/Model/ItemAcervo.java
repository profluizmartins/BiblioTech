package Model;

public class ItemAcervo {
    private int id;
    private String titulo;
    private int anoPublicacao;
    private boolean statusDisponivel;
    private boolean statusEmprestado;
    private boolean statusReservado;
    private boolean statusDescartado;

    public ItemAcervo(int id, String titulo, int anoPublicacao,
                      boolean statusDisponivel, boolean statusEmprestado,
                      boolean statusReservado, boolean statusDescartado) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.statusDisponivel = statusDisponivel;
        this.statusEmprestado = statusEmprestado;
        this.statusReservado = statusReservado;
        this.statusDescartado = statusDescartado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public boolean isStatusDisponivel() { return statusDisponivel; }
    public void setStatusDisponivel(boolean status) { this.statusDisponivel = status; }

    public boolean isStatusEmprestado() { return statusEmprestado; }
    public void setStatusEmprestado(boolean status) { this.statusEmprestado = status; }

    public boolean isStatusReservado() { return statusReservado; }
    public void setStatusReservado(boolean status) { this.statusReservado = status; }

    public boolean isStatusDescartado() { return statusDescartado; }
    public void setStatusDescartado(boolean status) { this.statusDescartado = status; }

}
