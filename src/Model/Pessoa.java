package Model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate dataNascimento;

    public Pessoa(String nome, String cpf, String endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    
    public int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    
    public String getNome(){ return nome; }
    public String getCpf() { return cpf; }
    public String getEndereco() { return endereco; }
    public LocalDate getDataNascimento() { return dataNascimento; } 

    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}
