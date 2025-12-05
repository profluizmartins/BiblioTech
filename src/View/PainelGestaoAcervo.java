package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Imports necessários
import Controller.AcervoController;
import model.ItemAcervo;

public class PainelGestaoAcervo extends JPanel {

    // (Botões e Tabela)
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JButton btnNovo;
    private JButton btnExcluir;
    private JButton btnAtualizar;

    // O Controller
    private AcervoController controller;

    public PainelGestaoAcervo() {
        // Configuração inicial do Painel
        this.setLayout(new BorderLayout()); 
        this.controller = new AcervoController();

        // Criando o Título 
        JLabel titulo = new JLabel("Gerenciamento de Acervo (Livros e Revistas)");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        // Criando a Tabela 
        String[] colunas = {"ID", "Título", "Ano", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0); 
        tabela = new JTable(modeloTabela);
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);

        // Criando os Botões 
        JPanel painelBotoes = new JPanel(); 
        
        btnNovo = new JButton("Novo Item");
        btnExcluir = new JButton("Excluir Item");
        btnAtualizar = new JButton("Atualizar Tabela");

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnAtualizar);

        this.add(painelBotoes, BorderLayout.SOUTH);

        // Ações
        configurarAcoes();
    }

    private void configurarAcoes() {
        
        // Botão ATUALIZAR
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDadosNaTabela();
            }
        });

        // Botão EXCLUIR
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();

                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um item na tabela para excluir.");
                    return;
                }

                int idParaExcluir = (int) tabela.getValueAt(linhaSelecionada, 0);
                String titulo = (String) tabela.getValueAt(linhaSelecionada, 1);

                int confirmacao = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja excluir o item '" + titulo + "'?", 
                        "Confirmar Exclusão", 
                        JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    try {
                        controller.excluir(idParaExcluir);
                        JOptionPane.showMessageDialog(null, "Item excluído com sucesso!");
                        carregarDadosNaTabela(); 
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Botão NOVO
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame janelaPai = (JFrame) SwingUtilities.getWindowAncestor(PainelGestaoAcervo.this);
                FormularioLivro formulario = new FormularioLivro(janelaPai);
                
                formulario.setVisible(true);
                
                carregarDadosNaTabela();
            }
        });
    }

    private void carregarDadosNaTabela() {
        modeloTabela.setRowCount(0);
        List<ItemAcervo> lista = controller.listarTodos();

        for (ItemAcervo item : lista) {
            Object[] linha = {
                item.getId(),
                item.getTitulo(),
                item.getAnoPublicacao(),
                item.getStatus()
            };
            modeloTabela.addRow(linha);
        }
    }

}