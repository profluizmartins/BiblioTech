package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Painel visual com o menu de relatórios disponíveis.
 * Basicamente é uma tela com 3 botões grandões empilhados pro usuário escolher
 * se quer ver o Top 10, Multas ou Atrasos. A lógica de gerar o relatório não tá aqui,
 * aqui só dispara o evento.
 */
public class PainelRelatorios extends JPanel {

    private JButton btnRelatorioTopLivros;
    private JButton btnRelatorioMultas;
    private JButton btnRelatorioAtrasos;

    /**
     * Construtor que já chama a inicialização dos componentes e arruma o layout.
     */
    public PainelRelatorios(){inicializarComponentes();configurarLayout();}

    /**
     * Cria os botões e define o tamanho deles
     * Os textos foram tirados do PDF de requisitos, mas a gente pode mudar se precisar.
     */
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

    /**
     * Organiza tudo na tela usando GridBagLayout pra centralizar e dar uma margem (Insets).
     */
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
    
    /**
     * Método pro Controller definir o que acontece quando clica no "Top 10".
     * @param acao O listener com a lógica.
     */
    public void addAcaoTopLivros(ActionListener acao){
        btnRelatorioTopLivros.addActionListener(acao);}
        
    /**
     * Método pro Controller definir o que acontece quando clica no "Multas".
     * @param acao O listener com a lógica.
     */
    public void addAcaoMultas(ActionListener acao){
        btnRelatorioMultas.addActionListener(acao);}
        
    /**
     * Método pro Controller definir o que acontece quando clica no "Atrasos".
     * @param acao O listener com a lógica.
     */
    public void addAcaoAtrasos(ActionListener acao){
        btnRelatorioAtrasos.addActionListener(acao);}
}