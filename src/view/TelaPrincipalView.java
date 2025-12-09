package view;
import javax.swing.*;
import contracts.IModulo;

import java.awt.*;
/*import java.awt.event.ActionEvent;
*/
/** 
 * Classe que apresenta a tela principal 
 * 
 */
public class TelaPrincipalView extends JFrame{

	//Componentes de layout e stts
	private JPanel painelCentral;
	private CardLayout cardLayout;
	private JLabel lblStatus;

	//Menu
	JMenuBar barramenu;

	//menu GESTÃO DE ACERVO
	JMenu menugestaoDeAcervo;
	JMenuItem adicionarObra, editarObra, listarObra, removerObra;
	
	//menu GESTÃO DE USUÁRIOS
	JMenu menugestaoDeUsuarios;
	JMenuItem adicionarUsuario, editarUsuario, listarUsuario, removerUsuario;
	
	//menu GESTÃO DE EMPRÉSTIMOS
	JMenu menugestaoDeEmprestimo;
	JMenuItem realizarEmprestimo;
	
	//menu GESTÃO DE DEVOLUÇÕES
	JMenu menugestaoDeDevolucoes;
	JMenuItem realizarDevolucao;
	
	//menu GESTÃO DE RESERVAS
	JMenu menugestaoDeReservas;
	JMenuItem reservarItem;
	
	//menu GESTÃO DE MULTAS E PAGAMENTOS
	JMenu menugestaoDeMultas;
	JMenuItem gerarMultas;
	JMenuItem registrarPagamentos;
	
	//menu PESQUISA E RELATORIOS
	JMenu menuPesquisaseRelatorios;
	JMenuItem realizarPesquisa;
	JMenuItem gerarRelatorios;
	
	//menu ADM SISTEMA
	JMenu menuAdministracaoFuncionarios;
	JMenuItem adicionarFuncionario;
	JMenuItem editarFuncionario;
	JMenuItem listarFuncionario;
	JMenuItem removerFuncionario;
	
	public TelaPrincipalView() {
        configurarJanela();
        inicializarMenus();
        inicializarAreaCentral(); //CardLayout
        inicializarBarraStatus(); //Status
    }

	private void configurarJanela() {
        setTitle("BiblioTech");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); //organizar Menu (Topo), Centro e Status (Rodapé)
    }

	private void inicializarMenus() {
        barramenu = new JMenuBar();
		setJMenuBar(barramenu); //Menu aqui
		//menu gestão de acervo;
		//cria a barra + adiciona na janela;
		menugestaoDeAcervo = new JMenu("Gestão de Acervo");
		barramenu.add(menugestaoDeAcervo);
		//descreve cada item;
		adicionarObra = new JMenuItem("Adicionar Obra");
		editarObra = new JMenuItem("Editar Obra");
		listarObra = new JMenuItem("Listar Obras do acervo");
		removerObra = new JMenuItem("Remover Obras do acervo");
		//adiciona na barra de menu;
		menugestaoDeAcervo.add(adicionarObra);
		menugestaoDeAcervo.add(editarObra);
		menugestaoDeAcervo.add(listarObra);
		menugestaoDeAcervo.add(removerObra);
		
		//menu gestao de usuarios
		//cria a barra + adiciona na janela;
		menugestaoDeUsuarios = new JMenu("Gestão de Usuários");
		barramenu.add(menugestaoDeUsuarios);
		//descreve cada item;
		adicionarUsuario = new JMenuItem("Novo usuario");
		editarUsuario = new JMenuItem("Editar usuario");
		listarUsuario = new JMenuItem("Listar usuários");
		removerUsuario = new JMenuItem("Remover usuários");
		//adiciona na barra de menu;
		menugestaoDeUsuarios.add(adicionarUsuario);
		menugestaoDeUsuarios.add(editarUsuario);
		menugestaoDeUsuarios.add(listarUsuario);
		menugestaoDeUsuarios.add(removerUsuario);
		
		//menu gestao de emprestimos
		//cria a barra + adiciona na janela;
		menugestaoDeEmprestimo = new JMenu("Gestão de Empréstimos");
		barramenu.add(menugestaoDeEmprestimo);
		//descreve cada item;
		realizarEmprestimo = new JMenuItem("Realizar empréstimo");
		//adiciona na barra de menu;
		menugestaoDeEmprestimo.add(realizarEmprestimo);
		
		//menu gestão de devoluções
		//cria a barra + adiciona na janela;
		menugestaoDeDevolucoes = new JMenu("Gestão de Devoluções");
		barramenu.add(menugestaoDeDevolucoes);
		//descreve cada item
		realizarDevolucao = new JMenuItem("Realizar devolução");
		//adiciona na barra de menu
		menugestaoDeDevolucoes.add(realizarDevolucao);
		
		//menu Gestão de reservas
		//cria a barra + adiciona na janela;
		menugestaoDeReservas = new JMenu("Gestão de Reservas");
		barramenu.add(menugestaoDeReservas);
		//descreve cada item
		reservarItem = new JMenuItem("Realizar reserva");
		//adiciona na barra de menu
		menugestaoDeReservas.add(reservarItem);
		
		//menu gestao de multas e pagamentos
		//cria a barra + adiciona na janela;
		menugestaoDeMultas = new JMenu("Gestão de Multas e Pagamentos");
		barramenu.add(menugestaoDeMultas);
		//descreve cada item
		gerarMultas = new JMenuItem("Gerar Multas");
		registrarPagamentos = new JMenuItem("Registrar Pagamentos");
		//adiciona na barra de menu
		menugestaoDeMultas.add(gerarMultas);
		menugestaoDeMultas.add(registrarPagamentos);
		
		//menu pesquisas e relatorios
		//cria a barra + adiciona na janela;
		menuPesquisaseRelatorios = new JMenu("Pesquisas e Relatorios");
		barramenu.add(menuPesquisaseRelatorios);
		//descreve cada item
		realizarPesquisa = new JMenuItem("Realizar Pesquisas");
		gerarRelatorios = new JMenuItem("Gerar Relatorios");
		//adiciona na barra de menu
		menuPesquisaseRelatorios.add(realizarPesquisa);
		menuPesquisaseRelatorios.add(gerarRelatorios);
		
		//menu Adm sistema
		//cria a barra + adiciona na janela;
		menuAdministracaoFuncionarios = new JMenu("Gestão de Funcionários");
	    barramenu.add(menuAdministracaoFuncionarios);
		//descreve cada item;
		adicionarFuncionario = new JMenuItem("Novo Funcionário");
		editarFuncionario = new JMenuItem("Editar Funcionário");
		listarFuncionario = new JMenuItem("Listar Funcionário");
		removerFuncionario = new JMenuItem("Remover Funcionário");
		//adiciona na barra de menu;
		menuAdministracaoFuncionarios.add(adicionarFuncionario);
		menuAdministracaoFuncionarios.add(editarFuncionario);
		menuAdministracaoFuncionarios.add(listarFuncionario);
		menuAdministracaoFuncionarios.add(removerFuncionario);
		
		//Botão arquivo -> logout
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> logout()); // Chama o método que fecha a tela
        menuArquivo.add(itemSair);
        barramenu.add(menuArquivo);
	}
		//
		private void inicializarAreaCentral() {
        // Implementação do CardLayout
        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);
        painelCentral.setBackground(Color.LIGHT_GRAY); //Cor de fundo só para teste

        // Tela de Boas Vindas (Aparece quando abre)
        JLabel labelBoasVindas = new JLabel("BEM VINDOS À BIBLIOTECH!", SwingConstants.CENTER);
        labelBoasVindas.setFont(new Font("Serif", Font.BOLD, 45));
        painelCentral.add(labelBoasVindas, "HOME"); // "HOME" é o nome dessa carta

        add(painelCentral, BorderLayout.CENTER);
    }
	private void inicializarBarraStatus() {
        //implementação da barra de status no rodapé
        lblStatus = new JLabel(" Usuário não logado");
        lblStatus.setBorder(BorderFactory.createEtchedBorder()); 
        lblStatus.setPreferredSize(new Dimension(getWidth(), 30));
        add(lblStatus, BorderLayout.SOUTH);
    }
	//Atualiza o texto do rodapé com o nome e cargo do usuário logado.
    public void atualizarStatus(String nome, String cargo) {
        lblStatus.setText(" Bem-vindo(a), " + nome + " | Cargo: " + cargo);
    }
	/**
     * Adiciona o painel de um grupo ao CardLayout.
     * @param modulo Interface do módulo do grupo
     * @param nomeIdentificador Nome interno para o CardLayout (ex: "MODULO_ACERVO")
     */
    public void adicionarPainelModulo(IModulo modulo, String nomeIdentificador) {
        painelCentral.add(modulo.getPainel(), nomeIdentificador);
    }
	/**
     * Troca a tela central para o módulo desejado.
     * Ex: mostrarPainel("MODULO_ACERVO")
     */
    public void mostrarPainel(String nomeIdentificador) {
        cardLayout.show(painelCentral, nomeIdentificador);
    }

    private void logout() {
        this.dispose();
        new TelaLogin().setVisible(true);
    }

	// --- GETTERS DOS MENUS
    // Para o Controller acessar

    // Getters do Acervo
    public JMenuItem getItemAdicionarObra() { return adicionarObra; }
    public JMenuItem getItemListarObra() { return listarObra; }

    // Getters de Usuários
    public JMenuItem getItemAdicionarUsuario() { return adicionarUsuario; }
    public JMenuItem getItemListarUsuario() { return listarUsuario; }

    // Getters de Empréstimo
    public JMenuItem getItemRealizarEmprestimo() { return realizarEmprestimo; }
    
    // Getters de Devolução
    public JMenuItem getItemRealizarDevolucao() { return realizarDevolucao; }

    // Getters de ADM
	public JMenuItem getItemListarFuncionario() {return listarFuncionario;}
}
