package controller;

import model.IAcervoRepositorio;
import model.IUsuarioRepositorio;
import model.excecoes.DadosInsuficientesException;
import model.excecoes.NenhumResultadoEncontradoException;
import view.PainelRelatorios;
import view.TelaRelatorioDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelRelatoriosController {

    private PainelRelatorios view;
    private RelatorioController relatorioController;

    public PainelRelatoriosController(PainelRelatorios view,
                                      IAcervoRepositorio acervoRepo,
                                      IUsuarioRepositorio usuarioRepo) {
        this.view = view;
        this.relatorioController = new RelatorioController(acervoRepo, usuarioRepo);
        registrarEventos();
    }

    private void registrarEventos() {

        // 1. TOP LIVROS (Usa a lógica de fallback "Mais Procurados/Reservados")
        view.addAcaoTopLivros(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorioTopLivros();
            }
        });

        // 2. MULTAS (Já existia)
        view.addAcaoMultas(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorioUsuariosComMulta();
            }
        });

        // 3. ITENS EMPRESTADOS (Antigo "Atrasos")
        view.addAcaoAtrasos(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorioItensEmprestados();
            }
        });

        // 4. ESTATÍSTICAS (Novo)
        view.addAcaoEstatisticas(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarEstatisticas();
            }
        });
    }

    // --- MÉTODOS AUXILIARES ---

    private void gerarRelatorioTopLivros() {
        try {
            List<String> lista = relatorioController.gerarRelatorioItensMaisEmprestados();
            exibirLista("Itens Mais Procurados (Fila de Espera)", lista);
        } catch (DadosInsuficientesException e) {
            exibirDialogo("Aviso", e.getMessage());
        }
    }

    private void gerarRelatorioUsuariosComMulta() {
        try {
            List<Object> usuarios = relatorioController.gerarRelatorioUsuariosComMulta();
            StringBuilder sb = new StringBuilder();
            for (Object u : usuarios) sb.append("• ").append(u.toString()).append("\n");
            
            exibirDialogo("Usuários Inadimplentes", sb.toString());
        } catch (NenhumResultadoEncontradoException e) {
            exibirDialogo("Aviso", e.getMessage());
        }
    }

    private void gerarRelatorioItensEmprestados() {
        try {
            List<String> lista = relatorioController.gerarRelatorioItensEmprestados();
            exibirLista("Itens Atualmente Emprestados", lista);
        } catch (NenhumResultadoEncontradoException e) {
            exibirDialogo("Aviso", e.getMessage());
        }
    }

    private void gerarEstatisticas() {
        try {
            String stats = relatorioController.gerarResumoEstatisticoAcervo();
            exibirDialogo("Estatísticas do Acervo", stats);
        } catch (NenhumResultadoEncontradoException e) {
            exibirDialogo("Erro", e.getMessage());
        }
    }

    // Função utilitária para transformar List<String> em texto corrido
    private void exibirLista(String titulo, List<String> lista) {
        StringBuilder builder = new StringBuilder();
        for (String item : lista) {
            builder.append(item).append("\n");
        }
        exibirDialogo(titulo, builder.toString());
    }

    private void exibirDialogo(String titulo, String conteudo) {
        Frame janelaPai = (Frame) SwingUtilities.getWindowAncestor(view);
        new TelaRelatorioDialog(janelaPai, titulo, conteudo).setVisible(true);
    }
}