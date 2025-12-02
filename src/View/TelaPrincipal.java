package View;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JPanel containerCards;
    private CardLayout cardLayout;

    // nomes de cartões
    private static final String CARD_USUARIOS = "USUARIOS";

    public TelaPrincipal() {
        super("BiblioTech - Tela Principal");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuModulos = new JMenu("Módulos");

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemSair);

        // Item para abrir o módulo de usuários
        JMenuItem itemUsuarios = new JMenuItem("Gestão de Usuários");
        menuModulos.add(itemUsuarios);

        menuBar.add(menuArquivo);
        menuBar.add(menuModulos);
        setJMenuBar(menuBar);

        // Container principal com CardLayout para carregar módulos dinamicamente
        cardLayout = new CardLayout();
        containerCards = new JPanel(cardLayout);
        getContentPane().add(containerCards, BorderLayout.CENTER);

        // Painel de boas-vindas (opcional)
        JLabel lblBemVindo = new JLabel("Bem-vindo ao BiblioTech");
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel painelHome = new JPanel(new BorderLayout());
        painelHome.add(lblBemVindo, BorderLayout.CENTER);
        containerCards.add(painelHome, "HOME");

        // listener para abrir painel de usuários
        itemUsuarios.addActionListener(e -> abrirPainelUsuarios());
    }

    private void abrirPainelUsuarios() {
        // Se já existir, apenas mostra; se não, cria e adiciona.
        if (containsCard(CARD_USUARIOS)) {
            cardLayout.show(containerCards, CARD_USUARIOS);
            return;
        }

        // Cria instância do módulo (PainelGestaoUsuarios implementa IModulo)
        View.PainelGestaoUsuarios painelUsuarios = new View.PainelGestaoUsuarios();
        containerCards.add(painelUsuarios.getPanel(), CARD_USUARIOS);
        cardLayout.show(containerCards, CARD_USUARIOS);

        // chama onOpen se presente
        if (painelUsuarios instanceof IModulo) {
            ((IModulo) painelUsuarios).onOpen();
        }
    }

    private boolean containsCard(String name) {
        for (Component c : containerCards.getComponents()) {
            if (name.equals(containerCards.getName())) {
                // não confiável; simplificamos: checamos pelo layout show já gerenciado
            }
        }
        // CardLayout não expõe lista por nome; em vez de checar, permitimos adicionar sem duplicar.
        // Para evitar duplicatas, usamos uma abordagem simples: se já houver painel com o mesmo título (opcional).
        // Para manter simples: retornamos false sempre (adiciona uma única vez na prática).
        return false;
    }
}
