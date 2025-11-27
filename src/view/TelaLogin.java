package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de login do sistema BiblioTech
 */

public class TelaLogin extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton btnOk;
    private JButton btnCancelar;

    /**
     * Construtor da classe TelaLogin.
     * 
     * Ele inicializa todos os componentes da interface gráfico. 
     * 
     * A interface gráfica foi definida de modo que o espaçamento entre os seus componentes permaneça o mesmo, independente do tamanho da janela.
     */

    public TelaLogin() {

        // Centraliza e define o nome e o tamanho inicial da janela.

        setTitle("BiblioTech - Login");
        setSize(400, 250);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configura o "layout manager" para usar o GridBagLayout e cria um objeto GridBagConstraints para definir o posicionamento dos componentes.

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Criação de um "label" para o "título" e configuração.

        JLabel titulo = new JLabel("Bem-vindo(a)", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 15, 0); 
        add(titulo, gbc);

        // "Reset" de configurações.

        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        
        // Posicionamento do "label" do login.

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Login:"), gbc);

        // Posicianamento do campo de login.

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        campoLogin = new JTextField(12);
        add(campoLogin, gbc);

        // Posicionamento do "label" da senha.

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Senha:"), gbc);

        // Posicionamento do campo da senha.

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        campoSenha = new JPasswordField(12);
        add(campoSenha, gbc);

        // Configuração do painel que contém os dois botões.

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 0, 20, 0);

        // Este comentário tem como única função verificar se um dos meus colegas leu o código.

        // Criação do painel de botões.

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnOk = new JButton("OK");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnOk);
        painelBotoes.add(btnCancelar);
        
        add(painelBotoes, gbc);
    }
}