package Model;

import java.time.LocalDate;

public class Usuario {
    private int matricula;
    private LocalDate dataNascimento;
    private boolean statusAtivo;
    private boolean statusSuspensoPorMulta;

    public Usuario(int matricula, LocalDate dataNascimento,
                   boolean statusAtivo, boolean statusSuspensoPorMulta) {
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.statusAtivo = statusAtivo;
        this.statusSuspensoPorMulta = statusSuspensoPorMulta;
    }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public boolean isStatusAtivo() { return statusAtivo; }
    public void setStatusAtivo(boolean statusAtivo) { this.statusAtivo = statusAtivo; }

    public boolean isStatusSuspensoPorMulta() { return statusSuspensoPorMulta; }
    public void setStatusSuspensoPorMulta(boolean statusSuspensoPorMulta) { this.statusSuspensoPorMulta = statusSuspensoPorMulta; }
}
