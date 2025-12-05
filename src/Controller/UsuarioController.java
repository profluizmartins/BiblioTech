package Controller;

import java.time.LocalDate;
import javax.swing.JOptionPane;

import Exceptions.CPFInvalidoException;
import Exceptions.CampoObrigatorioException;
import Exceptions.MenorDeIdadeException;
import Exceptions.UsuarioJaCadastradoException;
import Exceptions.ValidacaoException;
import Exceptions.OperacaoNaoPermitidaException;

import model.Usuario;
import model.dao.UsuarioRepositorio;

/**
 * @author Grupo 2 
 */

public class UsuarioController {

    private UsuarioRepositorio repositorio;

    public UsuarioController() {
        this.repositorio = new UsuarioRepositorio();
    }

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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarUsuario(String nome, String cpf, String endereco, LocalDate dataNascimento) {
        try {
            validarCampos(nome, cpf, endereco, dataNascimento);

            Usuario existente = repositorio.buscarPorCpf(cpf);

            if (existente == null) {
                // CORREÇÃO: Juntei as duas frases com ": "
                throw new OperacaoNaoPermitidaException("Atualização: Usuário com CPF '" + cpf + "' não existe.");
            }

            existente.setNome(nome);
            existente.setEndereco(endereco);
            existente.setDataNascimento(dataNascimento);

            repositorio.atualizar(existente);

            JOptionPane.showMessageDialog(null,
                    "Dados do usuário '" + nome + "' atualizados com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirUsuario(String cpf) {
        try {
            if (cpf == null || cpf.isBlank()) {
                throw new CampoObrigatorioException("CPF");
            }

            Usuario existente = repositorio.buscarPorCpf(cpf);

            if (existente == null) {
                throw new OperacaoNaoPermitidaException("Exclusão: Usuário não encontrado");
            }

            if (!"ATIVO".equals(existente.getStatus().name())) {

                throw new OperacaoNaoPermitidaException("Exclusão: Usuário está com pendências no sistema");
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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public java.util.List<Usuario> listarUsuarios() {
        return repositorio.buscarTodos();
    }

    public Usuario buscarUsuarioPorCpf(String cpf) throws Exception {
        Usuario u = repositorio.buscarPorCpf(cpf);

        if (u == null) {
            throw new OperacaoNaoPermitidaException("Busca: Nenhum usuário encontrado com o CPF informado");
        }

        return u;
    }

    private void validarCampos(String nome, String cpf, String endereco, LocalDate dataNascimento) throws Exception {

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