package model_temp;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe que representa um Usuário (Membro da Biblioteca).
 * Herda de Pessoa e adiciona atributos específicos (matricula, dataNascimento, status).
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Henrique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class Usuario extends Pessoa {

    /**
     * Define o conjunto fixo de status possíveis para um Usuário.
     * Está aninhado dentro da classe Usuario.
     */
    public enum TipoStatus {
        ATIVO,       
        SUSPENSOPORMULTA,     
    }

    private String matricula; 
    private TipoStatus status;
    private LocalDate dataNascimento; 

    /**
     * Construtor da classe Usuario.
     * Chama o construtor da superclasse Pessoa com os 4 parâmetros.
     * @param id O ID único da pessoa (passado ao construtor Pessoa).
     * @param nome O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param endereco O endereço do usuário.
     * @param dataNascimento A data de nascimento do usuário.
     */
    public Usuario(String nome, String cpf, String endereco, LocalDate dataNascimento) {
        super(nome, cpf, endereco);
        this.dataNascimento = dataNascimento; 
        this.matricula = "";
        this.status = TipoStatus.ATIVO;
    }
    
    /**
     * Método que calcula a idade da pessoa.
     * @return A idade atual da pessoa em anos.
     */
    public int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    
    //getters e setters

    /**
     * Obtém o status atual da matricula.
     * @return A matricula da pessoa.
     */
    public String getMatricula() { return matricula; }

    /**
     * Obtém o status atual do status.
     * @return O status da pessoa.
     */
    public TipoStatus getStatus() { return status; }

    /**
     * Obtém a data de nascimento da pessoa.
     * @return A data de nascimento.
     */
    public LocalDate getDataNascimento() { return dataNascimento; }
    
    /**
     * Atualiza o status da matricula.
     * @param nome A nova matricula a ser definido.
     */
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    /**
     * Atualiza o status do status.
     * @param nome O novo status a ser definido.
     */
    public void setStatus(TipoStatus status) { this.status = status; }

    /**
     * Atualiza a data de nascimento da pessoa.
     * @param dataNascimento A nova data de nascimento (LocalDate).
     */
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}