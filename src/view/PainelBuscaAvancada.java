package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Painel principal da tela de Busca Avançada.
 * Aqui ficam os campos de filtro (Título, Autor, Ano) na parte de cima
 * e a JTable no meio pra mostrar os resultados que voltaram do banco (ou do mock)
 */
public class PainelBuscaAvancada extends JPanel {

    private JTextField Titulo;
    private JTextField Autor;
    private JTextField Ano;
    private JTextField Id;
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
        Id= new JTextField(10);
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
     * Organiza os painéis.
     * MODIFICAÇÃO: O painelFiltros agora usa BorderLayout para jogar o botão para a DIREITA.
     */
    private void configurarLayout(){
        this.setLayout(new BorderLayout(10,10));

        // --- INICIO DA MUDANÇA PARA O BOTÃO NA DIREITA ---
        
        // O Painel container dos filtros usa BorderLayout
        JPanel painelFiltros = new JPanel(new BorderLayout());
        painelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de Pesquisa"));

        // Cria um sub-painel apenas para os campos (inputs) ficarem na esquerda
        JPanel painelCampos=new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelCampos.add(new JLabel("Título:"));painelCampos.add(Titulo);
        painelCampos.add(new JLabel("Autor:"));painelCampos.add(Autor);
        painelCampos.add(new JLabel("Ano:"));painelCampos.add(Ano);
        painelCampos.add(new JLabel("ID:"));painelCampos.add(Id);

        // Adiciona os campos no CENTRO e o botão no LESTE (Direita)
        painelFiltros.add(painelCampos, BorderLayout.CENTER);
        painelFiltros.add(bBuscar, BorderLayout.EAST);

        // --- FIM DA MUDANÇA ---

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
     * Getter de ID.
     * @return ID digitado.
     */
    public String getId(){return Id.getText();}

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
        for (Object[] linha:dados){Model.addRow(linha);}
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