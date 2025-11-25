package Model;

import java.time.LocalDate;

public class Usuario {
    private String nome;
    private LocalDate dataNascimento;
    private boolean statusAtivo;
    private boolean statusSuspensoPorMulta;


    public Usuario(String nome, LocalDate dataNascimento,
                   boolean statusAtivo, boolean statusSuspensoPorMulta) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusAtivo = statusAtivo;
        this.statusSuspensoPorMulta = statusSuspensoPorMulta;

    }

    public String getNome() { return nome; }

    public void setMatricula(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public boolean isStatusAtivo() { return statusAtivo; }

    public void setStatusAtivo(boolean statusAtivo) { this.statusAtivo = statusAtivo; }

    public boolean isStatusSuspensoPorMulta() { return statusSuspensoPorMulta; }

    public void setStatusSuspensoPorMulta(boolean statusSuspensoPorMulta) { this.statusSuspensoPorMulta = statusSuspensoPorMulta; }


}
