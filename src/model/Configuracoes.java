package model;

/**
 * Armazena e gerencia as configurações gerais do sistema.
 * Implementado no padrão Singleton.
 * @author Grupo 9
 */
public class Configuracoes {

    private static Configuracoes instancia;

    private int diasEmprestimoPadrao;
    private double valorMultaDiaria;
    private int limiteEmprestimosPorUsuario;

    /**
     * Construtor privado do Singleton.
     */
    private Configuracoes() {
        this.diasEmprestimoPadrao = 15;
        this.valorMultaDiaria = 1.0;
        this.limiteEmprestimosPorUsuario = 3;
    }

    /**
     * Obtém a instância única.
     */
    public static Configuracoes getInstancia() {
        if (instancia == null) {
            instancia = new Configuracoes();
        }
        return instancia;
    }

    public int getDiasEmprestimoPadrao() { return diasEmprestimoPadrao; }
    public void setDiasEmprestimoPadrao(int valor) { this.diasEmprestimoPadrao = valor; }

    public double getValorMultaDiaria() { return valorMultaDiaria; }
    public void setValorMultaDiaria(double valor) { this.valorMultaDiaria = valor; }

    public int getLimiteEmprestimosPorUsuario() { return limiteEmprestimosPorUsuario; }
    public void setLimiteEmprestimosPorUsuario(int valor) { this.limiteEmprestimosPorUsuario = valor; }
}