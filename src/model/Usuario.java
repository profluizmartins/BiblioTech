package model;

import java.time.LocalDate;

// --- Entidade Pessoa (Base) ---
abstract class Pessoa {
    private int id;
    private String nome;

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
}

// --- Entidade Usuário (Grupo 3) ---
public class Usuario extends Pessoa {
    private String matricula;
    private String status;

    public static final String ATIVO = "Ativo";
    public static final String SUSPENSO_MULTA = "SuspensoPorMulta";

    public Usuario(int id, String nome, String matricula) {
        super(id, nome);
        this.matricula = matricula;
        this.status = ATIVO;
    }

    public String getMatricula() { return matricula; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

