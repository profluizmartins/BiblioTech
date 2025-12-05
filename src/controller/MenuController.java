package controller;

import view.TelaPrincipalView;
import contracts.IModulo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuController {

    private TelaPrincipalView view;

    public MenuController(TelaPrincipalView view) {
        this.view = view;
        inicializarBotoes();
    }

    //Define o que cada botão do menu faz.
    private void inicializarBotoes() {
        //GESTÃO DE ACERVO
        // Quando clicar em "Adicionar Obra"
        view.getItemAdicionarObra().addActionListener(e -> navegarPara("MODULO_ACERVO"));
        
        // Quando clicar em "Listar Obras"
        view.getItemListarObra().addActionListener(e -> navegarPara("MODULO_ACERVO"));


        //GESTÃO DE USUÁRIOS
        view.getItemAdicionarUsuario().addActionListener(e -> navegarPara("MODULO_USUARIO"));
        view.getItemListarUsuario().addActionListener(e -> navegarPara("MODULO_USUARIO"));


        //GESTÃO DE EMPRÉSTIMOS
        view.getItemRealizarEmprestimo().addActionListener(e -> navegarPara("MODULO_EMPRESTIMO"));
    }

    /**
     * Método auxiliar para trocar de tela.
     * Se o módulo não estiver carregado, avisa o usuário.
     */
    private void navegarPara(String nomeModulo) {
        try {
            // Tenta mostrar o painel solicitado
            view.mostrarPainel(nomeModulo);
        } catch (Exception e) {

        }
        
    }

    //Carrega demais modulos
    public void carregarModulo(IModulo modulo) {
        if (modulo != null) {
            // Cria um identificador único baseado no título (ex: "Gestão de Acervo" -> "MODULO_GESTAO DE ACERVO")
            String id = "MODULO_" + modulo.getTitulo().toUpperCase().replace(" ", "_");
            
            // Adiciona na View
            view.adicionarPainelModulo(modulo, id);
            
            // Mapeia o ID para usar no método navegarPara
            System.out.println("Módulo carregado: " + modulo.getTitulo());
        }
    }
}
