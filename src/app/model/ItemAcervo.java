package app.model;

//TESTE

public class ItemAcervo {

    private int id;
    private String titulo;
    private StatusItem status;

    public ItemAcervo(int id, String titulo, StatusItem status) {
        this.id = id;
        this.titulo = titulo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }
}