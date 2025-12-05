package model_temp.entites;

/**
 * Representa um funcionário cadastrado no sistema.
 * Herda os atributos e comportamentos básicos da classe Pessoa (Grupo 3).
 * @author Grupo 9
 */
public class Funcionario extends Pessoa {

    private String login;
    private String senhaHash;
    private String cargo;

    /**
     * Construtor completo de Funcionario.
     *
     * @param nome nome completo do funcionário
     * @param cpf CPF do funcionário
     * @param endereco endereço do funcionário
     * @param id identificador único (float, conforme Pessoa)
     * @param login login para acesso ao sistema
     * @param senhaHash senha já criptografada (hash)
     * @param cargo cargo desempenhado (ex: Admin, Atendente)
     */
    public Funcionario(String nome, String cpf, String endereco, int id,
                       String login, String senhaHash, String cargo) {

        super(nome, cpf, endereco, id);
        this.login = login;
        this.senhaHash = senhaHash;
        this.cargo = cargo;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", login='" + login + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
