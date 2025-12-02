package Controller;

import java.time.LocalDate;
import javax.swing.JOptionPane;

import Exceptions.CPFInvalidoException;
import Exceptions.CampoObrigatorioException;
import Exceptions.MenorDeIdadeException;
import Exceptions.UsuarioJaCadastradoException;
import Exceptions.ValidacaoException;
import Exceptions.OperacaoNaoPermitidaException;

import Model.Usuario;
import Model.UsuarioRepositorio;

/**
 * Classe responsável por gerenciar as operações relacionadas aos usuários.
 * Atua como intermediária entre as views e o repositório, aplicando regras
 * de validação e regras de negócio definidas no sistema.
 *
 * Operações implementadas:
 * - Criar usuário
 * - Atualizar usuário existente
 * - Excluir usuário
 * - Listar usuários
 * - Buscar usuário por CPF
 *
 * Todas as falhas são tratadas com exceções personalizadas e mensagens
 * exibidas por JOptionPane.
 * 
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Henrique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public class UsuarioController {

    private UsuarioRepositorio repositorio;

    /**
     * Construtor padrão que instancia o repositório de usuários.
     */
    public UsuarioController() {
        this.repositorio = new UsuarioRepositorio();
    }

    /**
     * Cria um novo usuário após validar todos os campos obrigatórios.
     *
     * @param nome Nome do usuário.
     * @param cpf CPF contendo 11 dígitos numéricos.
     * @param endereco Endereço completo.
     * @param dataNascimento Data de nascimento do usuário.
     */
    public void criarUsuario(String nome, String cpf, String endereco, LocalDate dataNascimento) {
        try {
            validarCampos(nome, cpf, endereco, dataNascimento);

            Usuario existente = repositorio.buscarPorCpf(cpf);
            if (existente != null) {
                throw new UsuarioJaCadastradoException(cpf);
            }

            Usuario novo = new Usuario(nome, cpf, endereco, dataNascimento);
            repositorio.salvar(novo);

            JOptionPane.showMessageDialog(null,
                    "Usuário '" + nome + "' cadastrado com sucesso!\nMatrícula: " + novo.getMatricula(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza os dados de um usuário identificado pelo CPF.
     *
     * @param nome Novo nome.
     * @param cpf CPF do usuário (não pode ser alterado).
     * @param endereco Novo endereço.
     * @param dataNascimento Nova data de nascimento.
     */
    public void atualizarUsuario(String nome, String cpf, String endereco, LocalDate dataNascimento) {
        try {
            validarCampos(nome, cpf, endereco, dataNascimento);

            Usuario existente = repositorio.buscarPorCpf(cpf);

            if (existente == null) {
                throw new OperacaoNaoPermitidaException("Atualização",
                        "Usuário com CPF '" + cpf + "' não existe.");
            }

            existente.setNome(nome);
            existente.setEndereco(endereco);
            existente.setDataNascimento(dataNascimento);

            repositorio.atualizar(existente);

            JOptionPane.showMessageDialog(null,
                    "Dados do usuário '" + nome + "' atualizados com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Exclui um usuário do sistema após validação e confirmação.
     *
     * @param cpf CPF do usuário a ser excluído.
     */
    public void excluirUsuario(String cpf) {
        try {
            if (cpf == null || cpf.isBlank()) {
                throw new CampoObrigatorioException("CPF");
            }

            Usuario existente = repositorio.buscarPorCpf(cpf);

            if (existente == null) {
                throw new OperacaoNaoPermitidaException("Exclusão", "Usuário não encontrado");
            }

            if (!"ATIVO".equals(existente.getStatus().name())) {
                throw new OperacaoNaoPermitidaException("Exclusão",
                        "Usuário está com pendências no sistema");
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Tem certeza que deseja excluir o usuário '" + existente.getNome() + "'?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                repositorio.excluir(cpf);

                JOptionPane.showMessageDialog(null,
                        "Usuário excluído com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retorna uma lista contendo todos os usuários cadastrados.
     *
     * @return Lista de usuários.
     */
    public java.util.List<Usuario> listarUsuarios() {
        return repositorio.buscarTodos();
    }

    /**
     * Busca um usuário específico pelo CPF.
     *
     * @param cpf CPF a ser consultado.
     * @return Usuário encontrado.
     */
    public Usuario buscarUsuarioPorCpf(String cpf) {
        Usuario u = repositorio.buscarPorCpf(cpf);

        if (u == null) {
            throw new OperacaoNaoPermitidaException("Busca",
                    "Nenhum usuário encontrado com o CPF informado");
        }

        return u;
    }

    /**
     * Aplica as validações de campo definidas nas regras do trabalho.
     *
     * @param nome Nome informado.
     * @param cpf CPF informado.
     * @param endereco Endereço informado.
     * @param dataNascimento Data de nascimento.
     */
    private void validarCampos(String nome, String cpf, String endereco, LocalDate dataNascimento) {

        if (nome == null || nome.trim().length() < 3) {
            throw new CampoObrigatorioException("Nome");
        }

        if (endereco == null || endereco.trim().isEmpty()) {
            throw new CampoObrigatorioException("Endereço");
        }

        if (cpf == null || cpf.trim().length() != 11 || !cpf.matches("\\d+")) {
            throw new CPFInvalidoException(cpf);
        }

        if (dataNascimento == null) {
            throw new ValidacaoException("Data de Nascimento inválida");
        }

        int idade = java.time.Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 10) {
            throw new MenorDeIdadeException(idade);
        }
    }
}
