package controller;

import model.IAcervoRepositorio;
import model.IUsuarioRepositorio;
import model.excecoes.DadosInsuficientesException;
import model.excecoes.NenhumResultadoEncontradoException;
import view.PainelBuscaAvancada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controller responsável por mediar a comunicação entre a interface gráfica
 * PainelBuscaAvancada (View) e a camada de regras de negócio (BuscaController).
 */
public class BuscaAvancadaController {

    private PainelBuscaAvancada view;
    private BuscaController buscaController;

    public BuscaAvancadaController(PainelBuscaAvancada view,IAcervoRepositorio acervoRepo,IUsuarioRepositorio usuarioRepo) {

        this.view = view;
        this.buscaController = new BuscaController(acervoRepo, usuarioRepo);

        registrarEventos(); 
    }

    private void registrarEventos() {
        view.addAcaoBuscar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarBusca();
            }
        });
    }

    /**
     * Método principal do controller. 
     * Alterado para listar todo o acervo caso nenhum campo seja preenchido.
     */
    private void executarBusca() {
        try {
            // Recupera o que o usuário digitou
            String titulo = view.getTitulo();
            String autor = view.getAutor();
            String anoTexto = view.getAno();

            List<Object> resultados;

            // Lógica de Prioridade e Busca
            if (!titulo.isBlank()) {
                resultados = buscaController.buscarPorTitulo(titulo);
            } else if (!autor.isBlank()) {
                resultados = buscaController.buscarPorAutor(autor);
            } else if (!anoTexto.isBlank()) {
                int ano = Integer.parseInt(anoTexto); 
                resultados = buscaController.buscarPorAno(ano);
            } else {
                //Se todos os campos estiverem vazios, busca TODOS os itens
                resultados = buscaController.listarAcervoCompleto();
            }

            // Envia resultados para exibição na tabela
            converterECarregarTabela(resultados);

        }catch(NumberFormatException ex){
            view.exibirMensagemErro("O campo 'Ano' deve conter apenas números.");
        }catch(DadosInsuficientesException|NenhumResultadoEncontradoException ex){
            view.exibirMensagemErro(ex.getMessage());
        }catch(Exception ex){
            ex.printStackTrace(); // Bom para debug no console
            view.exibirMensagemErro("Erro inesperado durante a execução da busca.");
        }
    }

    private void converterECarregarTabela(List<Object> lista) {
        if(lista==null)return;
        Object[][] matriz=new Object[lista.size()][5];
        for(int i=0;i<lista.size();i++){matriz[i]=(Object[])lista.get(i);}
        view.atualizarTabela(matriz);
    }
}