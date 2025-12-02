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

public class UsuarioController {

    private UsuarioRepositorio repositorio;

    public UsuarioController() {
        this.repositorio = new UsuarioRepositorio();
    }

    // MÉTODO 1 — CRIAR USUÁRIO

    public void criarUsuario(String nome, String cpf, String endereco, LocalDate dataNascimento) 
    {
        try {
            validarCampos(nome, cpf, endereco, dataNascimento);

            // Verificar se o CPF já existe
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

    // MÉTODO 2 — ATUALIZAR USUÁRIO

    public void atualizarUsuario(String nome, String cpf, String endereco, LocalDate dataNascimento) 
    {
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

    // MÉTODO 3 — EXCLUIR USUÁRIO

    public void excluirUsuario(String cpf) {
        try {
            if (cpf == null || cpf.isBlank()) {
                throw new CampoObrigatorioException("CPF");
            }

            Usuario existente = repositorio.buscarPorCpf(cpf);

            if (existente == null) {
                throw new OperacaoNaoPermitidaException("Exclusão", "Usuário não encontrado");
            }

            if (!"ATIVO".equals(existente.getStatus())) {
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

    // MÉTODO 4 — LISTAR TODOS

    public java.util.List<Usuario> listarUsuarios() {
        return repositorio.buscarTodos();
    }

    // MÉTODO 5 — BUSCAR POR CPF

    public Usuario buscarUsuarioPorCpf(String cpf) {
        Usuario u = repositorio.buscarPorCpf(cpf);

        if (u == null) {
            throw new OperacaoNaoPermitidaException("Busca",
                    "Nenhum usuário encontrado com o CPF informado");
        }

        return u;
    }

    // VALIDAÇÃO DE CAMPOS

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
