package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;


public class PainelBuscaAvancada extends JPanel {

    private JTextField Titulo;
    private JTextField Autor;
    private JTextField Ano;
    private JButton bBuscar;
    private JTable Resultados;
    private DefaultTableModel Model;
    private JLabel Status;

    public PainelBuscaAvancada(){inicializarComponentes();configurarLayout();}

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
    public String getTitulo(){return Titulo.getText();}
    public String getAutor(){return Autor.getText();}
    public String getAno(){return Ano.getText();}

    public void addAcaoBuscar(ActionListener acao){bBuscar.addActionListener(acao);}

    /**
     * Limpa a tabela e adiciona novas linhas
     * @param dados Matriz dos dados etc
     */
    public void atualizarTabela(Object[][] dados){
        Model.setRowCount(0); //Limpa a tabela
        for (Object[] linha:dados) {Model.addRow(linha);}
        Status.setText("Exibindo "+dados.length+" resultados para a busca.");
    }
    public void exibirMensagemErro(String mensagem){
        JOptionPane.showMessageDialog(this,mensagem,"Erro",JOptionPane.ERROR_MESSAGE);
    }
}