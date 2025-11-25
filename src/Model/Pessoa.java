package Model;

/**
 * Classe abstrata que representa a entidade base Pessoa.
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @version 1.0
 */
public abstract class Pessoa {
    
    private String nome;
    private String cpf;
    private String endereco;
    private int id;

    /**
     * Construtor da classe Pessoa.
     * @param nome O nome completo da pessoa.
     * @param cpf O Cadastro de Pessoa Física (CPF), usado como identificador único.
     * @param endereco O endereço residencial da pessoa.
     * @param id O identificador único numérico da pessoa.
     */
    public Pessoa(String nome, String cpf, String endereco, int id) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.id = 0;
    }

    //getters e setters

    /**
     * Obtém o status atual do nome.
     * @return O nome completo da pessoa.
     */
    public String getNome(){ return nome; }

    /**
     * Obtém o status atual do CPF.
     * @return O CPF da pessoa.
     */
    public String getCpf() { return cpf; }

    /**
     * Obtém o status atual do endereço.
     * @return O endereço da pessoa.
     */
    public String getEndereco() { return endereco; }

    /**
     * Obtém o id da pessoa.
     * @return O id.
     */
    public int getId() { return id; } 

    /**
     * Atualiza o status do nome.
     * @param nome O novo nome a ser definido.
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * Atualiza o status do CPF.
     * @param cpf O novo CPF a ser definido.
     */
    public void setCpf(String cpf) { this.cpf = cpf; }

    /**
     * Atualiza o status do endereço.
     * @param endereco O novo endereço a ser definido.
     */
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    /**
     * Atualiza o id da pessoa.
     * @param id O novo id.
     */
    public void setId(int id) { this.id = id; }
}
