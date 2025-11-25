import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Tela Principal do Grupo 2.
 */
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
        this.setLayout(new BorderLayout()); // Layout simples: Norte, Sul, Leste, Oeste, Centro
        this.controller = new AcervoController();

        // Criando o Título (Parte de Cima)
        JLabel titulo = new JLabel("Gerenciamento de Acervo (Livros e Revistas)");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        // Criando a Tabela (Parte do Centro)
        String[] colunas = {"ID", "Título", "Ano", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0); // 0 indica que começa sem linhas
        tabela = new JTable(modeloTabela);
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);

        // Criando os Botões (Parte de Baixo)
        JPanel painelBotoes = new JPanel(); // Um painelzinho só para os botões
        
        btnNovo = new JButton("Novo Item");
        btnExcluir = new JButton("Excluir Item");
        btnAtualizar = new JButton("Atualizar Tabela");

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnAtualizar);

        this.add(painelBotoes, BorderLayout.SOUTH);

        // (Ações)
        configurarAcoes();
    }

    // Método para configurar o que cada botão faz
    private void configurarAcoes() {
        
        // Ação do Botão ATUALIZAR
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDadosNaTabela();
            }
        });

        // Ação do Botão EXCLUIR
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

                // Pergunta se tem certeza (Requisito do PDF)
                int confirmacao = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja excluir o item '" + titulo + "'?", 
                        "Confirmar Exclusão", 
                        JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    try {
                        controller.excluir(idParaExcluir);
                        JOptionPane.showMessageDialog(null, "Item excluído com sucesso!");
                        carregarDadosNaTabela(); // Recarrega a tabela para sumir o item
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Ação do Botão NOVO
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria a janela de formulário
                JFrame janelaPai = (JFrame) SwingUtilities.getWindowAncestor(PainelGestaoAcervo.this);
                FormularioLivro formulario = new FormularioLivro(janelaPai);
                
                // Mostra a janela na tela
                formulario.setVisible(true);
                
                // Quando a janela fechar, atualizamos a tabela
                carregarDadosNaTabela();
            }
        });
    }

    // Método que vai no Controller, pega a lista e joga na Tabela
    private void carregarDadosNaTabela() {
        // Limpa a tabela atual
        modeloTabela.setRowCount(0);

        // Pede a lista para o controller
        List<ItemAcervo> lista = controller.listarTodos();

        // Adiciona cada item como uma linha na tabela
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

    /*   --- MÉTODO MAIN (Só para você testar essa tela agora) ---
    public static void main(String[] args) {
        JFrame janelaTeste = new JFrame("Teste do Grupo 2");
        janelaTeste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaTeste.setSize(600, 400); // Tamanho da janela
        
        PainelGestaoAcervo meuPainel = new PainelGestaoAcervo();
        janelaTeste.add(meuPainel);

        // Mostra a janela
        janelaTeste.setVisible(true);
    }
} */