package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PainelRelatorios extends JPanel {

    private JButton btnRelatorioTopLivros;
    private JButton btnRelatorioMultas;
    private JButton btnRelatorioAtrasos;
    // NOVO BOTÃO
    private JButton btnEstatisticas; 

    public PainelRelatorios(){
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes(){
        // Botões existentes
        btnRelatorioTopLivros = new JButton("Top Livros (Mais Procurados)");
        btnRelatorioMultas = new JButton("Usuários com Multas Pendentes");
        btnRelatorioAtrasos = new JButton("Listar Itens Emprestados"); // Renomeei para refletir a lógica atual
        
        // NOVO BOTÃO
        btnEstatisticas = new JButton("Resumo Estatístico do Acervo");

        Dimension btTamanho = new Dimension(500, 50);
        btnRelatorioTopLivros.setPreferredSize(btTamanho);
        btnRelatorioMultas.setPreferredSize(btTamanho);
        btnRelatorioAtrasos.setPreferredSize(btTamanho);
        btnEstatisticas.setPreferredSize(btTamanho); // Define tamanho
    }

    private void configurarLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        
        gbc.gridy = 0;
        this.add(new JLabel("Selecione o Relatório Desejado:"), gbc);

        gbc.gridy = 1;
        this.add(btnRelatorioTopLivros, gbc);
        gbc.gridy = 2;
        this.add(btnRelatorioMultas, gbc);
        gbc.gridy = 3;
        this.add(btnRelatorioAtrasos, gbc);
        
        // ADICIONAR NO LAYOUT
        gbc.gridy = 4;
        this.add(btnEstatisticas, gbc);
    }

    public void addAcaoTopLivros(ActionListener acao){ btnRelatorioTopLivros.addActionListener(acao); }
    public void addAcaoMultas(ActionListener acao){ btnRelatorioMultas.addActionListener(acao); }
    public void addAcaoAtrasos(ActionListener acao){ btnRelatorioAtrasos.addActionListener(acao); }
    
    // NOVO LISTENER
    public void addAcaoEstatisticas(ActionListener acao){ btnEstatisticas.addActionListener(acao); }
}