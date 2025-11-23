package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Painel principal da tela de Busca Avançada.
 * Aqui ficam os campos de filtro (Título, Autor, Ano) na parte de cima
 * e a JTable no meio pra mostrar os resultados que voltaram do banco (ou do mock)
 */
public class PainelBuscaAvancada extends JPanel {

    private JTextField Titulo;
    private JTextField Autor;
    private JTextField Ano;
    private JButton bBuscar;
    private JTable Resultados;
    private DefaultTableModel Model;
    private JLabel Status;

    /**
     * Construtor que já inicializa os componentes e monta o layout da tela
     */
    public PainelBuscaAvancada(){inicializarComponentes();configurarLayout();}

    /**
     * Instancia os TextFields, o botão e configura a Tabela.
     * Define as larguras das colunas e trava a edição das células pra ser só leitura.
     */
    private void inicializarComponentes() {
        Titulo=new JTextField(20);
        Autor=new JTextField(20);
        Ano=new JTextField(10);
        bBuscar=new JButton("Pesquisar");

        // Configuração da Tabela
        String[] colunas={"ID","Título","Autor","Ano","Status"};
        Model=new DefaultTableModel(colunas,0){
            @Override public boolean isCellEditable(int row,int column){return false;}
        };
        Resultados=new JTable(Model);

        Resultados.getColumnModel().getColumn(0).setPreferredWidth(30);
        Resultados.getColumnModel().getColumn(0).setMaxWidth(50);
        Resultados.getColumnModel().getColumn(1).setPreferredWidth(200);
        Resultados.getColumnModel().getColumn(2).setPreferredWidth(150);
        
        Status=new JLabel("Aguardando busca...");
    }

    /**
     * Organiza os painéis usando BorderLayout.
     * Coloca os filtros no Norte, a tabela no Centro (com scroll) e o status no Sul.
     */
    private void configurarLayout(){
        this.setLayout(new BorderLayout(10,10));

        //Painel de Filtros (Parte de cima)
        JPanel painelFiltros=new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de Pesquisa"));
        
        painelFiltros.add(new JLabel("Título:"));painelFiltros.add(Titulo);
        painelFiltros.add(new JLabel("Autor:"));painelFiltros.add(Autor);
        painelFiltros.add(new JLabel("Ano:"));painelFiltros.add(Ano);
        painelFiltros.add(bBuscar);

        //Painel da Tabela (Centro)
        JScrollPane scrollPane=new JScrollPane(Resultados);

        //Painel de Status (Parte de Baixo)
        JPanel painelStatus=new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelStatus.add(Status);

        this.add(painelFiltros,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(painelStatus,BorderLayout.SOUTH);
    }

    //Métodos do Controller pra View
    
    /**
     * @return O que o usuário digitou no campo Título.
     */
    public String getTitulo(){return Titulo.getText();}
    
    /**
     * @return O que o usuário digitou no campo Autor.
     */
    public String getAutor(){return Autor.getText();}
    
    /**
     * @return O que o usuário digitou no campo Ano.
     */
    public String getAno(){return Ano.getText();}

    /**
     * Registra quem vai escutar o clique no botão "Pesquisar".
     * @param acao O listener do Controller que faz a busca acontecer.
     */
    public void addAcaoBuscar(ActionListener acao){bBuscar.addActionListener(acao);}

    /**
     * Limpa a tabela antiga e preenche com as novas linhas que vieram da busca.
     * Também atualiza o texto de status lá embaixo.
     * @param dados Matriz de objetos (Object[][]) pronta pra jogar no DefaultTableModel.
     */
    public void atualizarTabela(Object[][] dados){
        Model.setRowCount(0); //Limpa a tabela
        for (Object[] linha:dados) {Model.addRow(linha);}
        Status.setText("Exibindo "+dados.length+" resultados para a busca.");
    }
    
    /**
     * Exibe um pop-up de erro simples caso dê ruim na validação ou na busca.
     * @param mensagem O texto do erro pra mostrar pro usuário.
     */
    public void exibirMensagemErro(String mensagem){
        JOptionPane.showMessageDialog(this,mensagem,"Erro",JOptionPane.ERROR_MESSAGE);
    }
}