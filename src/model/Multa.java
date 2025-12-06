package model;

import java.time.LocalDate;
import Model2.Usuario;
import appmodel.Emprestimo;
/**
 * Representa uma multa gerada por atraso na devolução de um item da biblioteca.
 * Esta classe compõe a camada Model e encapsula a regra de negócio para cálculo automático de valores.
 * * @author Grupo 7 - Gestão de Multas
 * @version 2.0 (Com lógica de negócio no construtor)
 */
public class Multa {

    /** Identificador único da multa. */
    private int id;

    /** O usuário associado a esta multa (o devedor). */
    private Usuario usuario;

    /** O empréstimo que originou o atraso. */
    private Emprestimo emprestimo;

    /** O valor monetário total da multa em Reais (R$). */
    private double valor;

    /** A data em que a multa foi gerada. */
    private LocalDate dataMulta;

    /** O status atual da multa (PENDENTE ou PAGA). */
    private StatusMulta status;

    /**
     * Construtor completo da multa que aplica a lógica de negócio exigida.
     * Calcula automaticamente o valor da multa com base nos dias de atraso e define a data atual.
     * * @param id Identificador único da multa.
     * @param usuario O usuário que está recebendo a multa.
     * @param emprestimo O empréstimo que foi devolvido com atraso.
     * @param diasAtraso O número de dias de atraso verificado na devolução.
     * @param valorMultaDiaria O valor da multa por dia (configuração vinda do Grupo 9).
     */
    public Multa(int id, Usuario usuario, Emprestimo emprestimo,
                 int diasAtraso, double valorMultaDiaria) {

        this.id = id;
        this.usuario = usuario;
        this.emprestimo = emprestimo;
        this.dataMulta = LocalDate.now(); // Define a data de hoje automaticamente
        
        // REGRA DE NEGÓCIO: O cálculo deve ser feito no Model
        this.valor = calcularMulta(diasAtraso, valorMultaDiaria);
        
        // Toda multa nasce como Pendente
        this.status = StatusMulta.PENDENTE;
    }

    /**
     * Lógica de Negócio: Calcula o valor total da multa.
     * Multiplica a quantidade de dias de atraso pelo valor da multa diária.
     * * @param diasAtraso Quantidade de dias que o item ficou atrasado.
     * @param valorMultaDiaria Valor cobrado por dia de atraso.
     * @return O valor total calculado (retorna 0.0 se diasAtraso for inválido).
     */
    public double calcularMulta(int diasAtraso, double valorMultaDiaria) {
        if (diasAtraso <= 0) {
            return 0.0;
        }
        return diasAtraso * valorMultaDiaria;
    }

    //Getters e Setters

    /**
     * Obtém o identificador único da multa.
     * @return O ID da multa.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da multa.
     * @param id O novo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o usuário associado à multa.
     * @return O objeto Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Obtém o empréstimo que gerou a multa.
     * @return O objeto Emprestimo.
     */
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    /**
     * Obtém o valor monetário da multa.
     * @return O valor em Reais.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Obtém a data em que a multa foi gerada.
     * @return A data da multa.
     */
    public LocalDate getDataMulta() {
        return dataMulta;
    }

    /**
     * Obtém o status atual da multa.
     * @return O status (PENDENTE ou PAGA).
     */
    public StatusMulta getStatus() {
        return status;
    }

    /**
     * Atualiza o status da multa.
     * @param status O novo status a ser definido.
     */
    public void setStatus(StatusMulta status) {
        this.status = status;
    }
}