package view;
import javax.swing.*;
import java.awt.*;
/*import java.awt.event.ActionEvent;
*/
/** 
 * Classe que apresenta a tela principal 
 * 
 */
public class TelaPrincipalView extends JFrame{
	
	JMenuBar barramenu;
	//menu GESTÃO DE ACERVO
	
	JMenu menugestaoDeAcervo;
	JMenuItem itemAdd1;
	JMenuItem itemEditar1;
	JMenuItem itemLer1;
	JMenuItem itemRemover1;
	
	//menu GESTÃO DE USUÁRIOS
	JMenu menugestaoDeUsuarios;
	JMenuItem itemAdd2;
	JMenuItem itemEditar2;
	JMenuItem itemLer2;
	JMenuItem itemRemover2;
	
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
	JMenuItem itemAdd3;
	JMenuItem itemEditar3;
	JMenuItem itemLer3;
	JMenuItem itemRemover3;
	
	
	
	public TelaPrincipalView() {
		
		barramenu = new JMenuBar();
		//menu gestão de acervo;
		//cria a barra + adiciona na janela;
		menugestaoDeAcervo = new JMenu("Gestão de Acervo");
		barramenu.add(menugestaoDeAcervo);
		//descreve cada item;
		itemAdd1 = new JMenuItem("Adicionar Obra");
		itemEditar1 = new JMenuItem("Editar Obra");
		itemLer1 = new JMenuItem("Listar Obras do acervo");
		itemRemover1 = new JMenuItem("Remover Obras do acervo");
		//adiciona na barra de menu;
		menugestaoDeAcervo.add(itemAdd1);
		menugestaoDeAcervo.add(itemEditar1);
		menugestaoDeAcervo.add(itemLer1);
		menugestaoDeAcervo.add(itemRemover1);
		
		//menu gestao de usuarios
		//cria a barra + adiciona na janela;
		menugestaoDeUsuarios = new JMenu("Gestão de Usuários");
		barramenu.add(menugestaoDeUsuarios);
		//descreve cada item;
		itemAdd2 = new JMenuItem("Novo usuario");
		itemEditar2 = new JMenuItem("Editar usuario");
		itemLer2 = new JMenuItem("Listar usuários");
		itemRemover2 = new JMenuItem("Remover usuários");
		//adiciona na barra de menu;
		menugestaoDeUsuarios.add(itemAdd2);
		menugestaoDeUsuarios.add(itemEditar2);
		menugestaoDeUsuarios.add(itemLer2);
		menugestaoDeUsuarios.add(itemRemover2);
		
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
		itemAdd3 = new JMenuItem("Novo Funcionário");
		itemEditar3 = new JMenuItem("Editar Funcionário");
		itemLer3 = new JMenuItem("Listar Funcionário");
		itemRemover3 = new JMenuItem("Remover Funcionário");
		//adiciona na barra de menu;
		menuAdministracaoFuncionarios.add(itemAdd3);
		menuAdministracaoFuncionarios.add(itemEditar3);
		menuAdministracaoFuncionarios.add(itemLer3);
		menuAdministracaoFuncionarios.add(itemRemover3);
		
		
		//janelas (com todas as views prontas, poderemos completar essa parte do codigo)
		
		JPanel panelMenus = new JPanel();
		panelMenus.setLayout(new GridLayout(1, 8));
		panelMenus.add(barramenu);
		panelMenus.setBackground(Color.gray);
		//setLayout(new BorderLayout());
		add(panelMenus, BorderLayout.NORTH);
		/*
		menugestaoDeAcervo.addActionListener(e -> trocarTela(new TelaHome()));
		menugestaoDeUsuarios.addActionListener(e -> trocarTela(new TelaUsuarios()));
		menugestaoDeEmprestimo.addActionListener(e -> trocarTela(new TelaProdutos()));
		menugestaoDeDevolucoes.addActionListener(e -> trocarTela(new TelaHome()));
		menugestaoDeReservas.addActionListener(e -> trocarTela(new TelaUsuarios()));
		menugestaoDeMultas.addActionListener(e -> trocarTela(new TelaProdutos()));
		menuPesquisaseRelatorios.addActionListener(e -> trocarTela(new TelaHome()));
		menuAdministracaoFuncionarios.addActionListener(e -> trocarTela(new TelaUsuarios()));
		*/ 
		
		setJMenuBar(barramenu);
		setSize(800,600);
		setTitle("BiblioTech");
		setLocationRelativeTo(null);
		setResizable(true);
		this.getContentPane().add(panelMenus);
		
		JLabel lb = new JLabel("BEM VINDOS À BIBLIOTECH!",
				SwingConstants.CENTER);
		lb.setFont(new Font("Serif",Font.BOLD,45));
		lb.setForeground(Color.black);
		panelMenus.add(lb);
	}
	public static void main(String[] args) {
		TelaPrincipalView tv = new TelaPrincipalView();
		//tv.pack();
		tv.setVisible(true);
		
		
		
		
	}



}
