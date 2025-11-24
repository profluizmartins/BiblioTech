package Model;

import java.time.LocalDate;

public class Usuario extends Pessoa {

    private String matricula; 
    private String status;

    public Usuario(String nome, String cpf, String endereco, LocalDate dataNascimento) {
        super(nome, cpf, endereco, dataNascimento);
        this.matricula = ""; 
        this.status = "ATIVO";
    }
    
    public String getMatricula() { return matricula; }
    public String getStatus() { return status; }
    
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setStatus(String status) { this.status = status; }
}