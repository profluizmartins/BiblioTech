package app.model;

//-------TESTE

public class Usuario {

    private String matricula;
    private String nome;
    private StatusUsuario status;

    public Usuario(String matricula, String nome, StatusUsuario status) {
        this.matricula = matricula;
        this.nome = nome;
        this.status = status;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }
}