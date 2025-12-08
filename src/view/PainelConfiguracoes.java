package view;

import javax.swing.*;
import java.awt.*;
import controller.ConfiguracaoController;
import model.Configuracoes;
import exception.ValidacaoException;

public class PainelConfiguracoes extends JPanel {
    
    private static final long serialVersionUID = 1L; // tentar tirar o warning

    private JTextField txtDias, txtMulta, txtLimite;
    private JButton btnSalvar;
    private ConfiguracaoController controller;

    public PainelConfiguracoes() {
        this.controller = new ConfiguracaoController();
        inicializarComponentes();
        carregarDadosAtuais();
    }

    private void inicializarComponentes() {
        // layout: 4 linhas, 2 colunas, espaçamento de 10px
        setLayout(new GridLayout(4, 2, 10, 10));
        
        // borda
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // dias
        add(new JLabel("Dias de empréstimo padrão:"));
        txtDias = new JTextField();
        add(txtDias);

        // valor da multa
        add(new JLabel("Valor da multa diária (R$):"));
        txtMulta = new JTextField();
        add(txtMulta);

        // lim livros
        add(new JLabel("Limite de livros por usuário:"));
        txtLimite = new JTextField();
        add(txtLimite);

        // Botão Salvar
        add(new JLabel("")); 
        btnSalvar = new JButton("Salvar Configurações");
        add(btnSalvar);

        // oq o botão faz
        btnSalvar.addActionListener(e -> {
            try {
                controller.salvarAlteracoes(
                    txtDias.getText(),
                    txtMulta.getText(),
                    txtLimite.getText()
                );
                
              
                JOptionPane.showMessageDialog(this, 
                    "Configurações do sistema atualizadas com sucesso!\n(Pode ser necessário reiniciar o sistema para aplicar todas as mudanças).");
                
            } catch (ValidacaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void carregarDadosAtuais() {
        Configuracoes config = controller.carregarConfiguracoesAtuais();
        
        txtDias.setText(String.valueOf(config.getDiasEmprestimoPadrao()));
        txtMulta.setText(String.valueOf(config.getValorMultaDiaria()));
        txtLimite.setText(String.valueOf(config.getLimiteEmprestimosPorUsuario()));
    }
}