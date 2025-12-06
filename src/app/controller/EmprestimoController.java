package app.controller;

import app.model.*;
import app.exception.*;

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class EmprestimoController {

    private final UsuarioRepositorio usuarioRepo;
    private final AcervoRepositorio itemRepo;
    private final EmprestimoRepositorio emprestimoRepo;
    private final Configuracoes config;

    private Usuario usuarioBuscado;
    private ItemAcervo itemBuscado;

    public EmprestimoController(
            UsuarioRepositorio usuarioRepo,
            AcervoRepositorio itemRepo,
            EmprestimoRepositorio emprestimoRepo,
            Configuracoes config) {

        this.usuarioRepo = usuarioRepo;
        this.itemRepo = itemRepo;
        this.emprestimoRepo = emprestimoRepo;
        this.config = config;
    }

    // buscar usuario

    public Usuario buscarUsuario(String matricula) {

        if (matricula == null || matricula.isBlank()) {
            throw new UsuarioNaoEncontradoException("Campo matrícula vazio.");
        }

        Usuario usuario = usuarioRepo.buscarPorMatricula(matricula);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(
                    "Nenhum usuário encontrado com a matrícula '" + matricula + "'.");
        }

        if (usuario.getStatus() == StatusUsuario.SuspensoPorMulta) {
            throw new UsuarioSuspensoException(
                    "Empréstimo BLOQUEADO: O usuário '" + usuario.getNome() + "' está suspenso por multas pendentes.");
        }

        usuarioBuscado = usuario;
        return usuario;
    }

    //buscar item

    public ItemAcervo buscarItem(String idTexto) {

        if (idTexto == null || idTexto.isBlank()) {
            throw new ItemNaoEncontradoException("Campo ID do item vazio.");
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            throw new ItemInvalidoException("O ID do item deve ser um número inteiro.");
        }

        ItemAcervo item = itemRepo.buscarPorId(id);

        if (item == null) {
            throw new ItemNaoEncontradoException(
                    "Nenhum item encontrado com o ID '" + id + "'.");
        }

        if (item.getStatus() != StatusItem.DISPONIVEL) {
            throw new ItemNaoDisponivelException(
                    "Empréstimo BLOQUEADO: O item '" + item.getTitulo() +
                    "' não está disponível (Status: " + item.getStatus() + ").");
        }

        itemBuscado = item;
        return item;
    }

    // confirmar emprestimo

    public Emprestimo confirmarEmprestimo() {

        if (usuarioBuscado == null) {
            throw new EmprestimoException("Busque um usuário antes de confirmar o empréstimo.");
        }

        if (itemBuscado == null) {
            throw new EmprestimoException("Busque um item antes de confirmar o empréstimo.");
        }

        int limite = config.getLimiteEmprestimos();

        long ativos = emprestimoRepo.listar().stream()
                .filter(e -> e.getUsuario().equals(usuarioBuscado)
                        && e.getStatus() == StatusEmprestimo.ATIVO)
                .count();

        if (ativos >= limite) {
            throw new EmprestimoException(
                    "Empréstimo BLOQUEADO: O usuário '" + usuarioBuscado.getNome() +
                    "' já atingiu o limite de " + limite + " empréstimos simultâneos.");
        }

        Emprestimo emp = emprestimoRepo.criarEmprestimo(
                usuarioBuscado,
                itemBuscado,
                LocalDate.now(),
                null
        );

        itemBuscado.setStatus(StatusItem.EMPRESTADO);

        JOptionPane.showMessageDialog(null,
                "Empréstimo realizado com sucesso!\n" +
                "O usuário '" + usuarioBuscado.getNome() +
                "' deve devolver o item '" + itemBuscado.getTitulo() +
                "' até " + emp.getDataPrevistaDevolucao() + ".");

        return emp;
    }
}
