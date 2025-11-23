package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class PainelRelatorios extends JPanel {

    private JButton btnRelatorioTopLivros;
    private JButton btnRelatorioMultas;
    private JButton btnRelatorioAtrasos;

    public PainelRelatorios(){inicializarComponentes();configurarLayout();}

    private void inicializarComponentes(){
        //Textos dos botões exemplo do PDF, podem mudar e tals
        btnRelatorioTopLivros=new JButton("Top 10 Livros Mais Emprestados");
        btnRelatorioMultas=new JButton("Usuários com Multas Pendentes");
        btnRelatorioAtrasos=new JButton("Itens em Atraso");
        
        //Estilização básica pros botões maiores
        Dimension btTamanho=new Dimension(300,50);
        btnRelatorioTopLivros.setPreferredSize(btTamanho);
        btnRelatorioMultas.setPreferredSize(btTamanho);
        btnRelatorioAtrasos.setPreferredSize(btTamanho);
    }

    private void configurarLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);//Margem dos botões
        gbc.gridx=0;
        
        gbc.gridy=0;
        this.add(new JLabel("Selecione o Relatório Desejado:"), gbc);

        gbc.gridy=1;
        this.add(btnRelatorioTopLivros, gbc);
        gbc.gridy=2;
        this.add(btnRelatorioMultas, gbc);
        gbc.gridy=3;
        this.add(btnRelatorioAtrasos, gbc);
    }

    //Métodos pro Controller registrar os eventos
    public void addAcaoTopLivros(ActionListener acao){
        btnRelatorioTopLivros.addActionListener(acao);}
    public void addAcaoMultas(ActionListener acao){
        btnRelatorioMultas.addActionListener(acao);}
    public void addAcaoAtrasos(ActionListener acao){
        btnRelatorioAtrasos.addActionListener(acao);}
}