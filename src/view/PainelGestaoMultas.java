package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.MultaController;
import model.Multa;


/**
 * Painel de Gestão de Multas.
 *
 * Exibe uma tabela com todas as multas pendentes e
 * permite registrar o pagamento informando o ID da multa.
 *
 * Este painel pertence à camada View e NÃO contém lógica de negócio.
 * Toda a lógica é delegada ao MultaController.
 */
public class PainelGestaoMultas extends JPanel {

    private final MultaController multaController;

    private JTable tabelaMultas;
    private DefaultTableModel tabelaModel;
    private JButton btnRegistrarPagamento;

    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Cria o painel de gestão de multas.
     *
     * @param multaController controller responsável pelas operações de multas
     */
    public PainelGestaoMultas(MultaController multaController) {
        this.multaController = multaController;
        inicializarComponentes();
        configurarLayout();
        carregarDadosTabela();
    }

    /**
     * Inicializa os componentes gráficos do painel.
     */
    private void inicializarComponentes() {
        
        tabelaModel = new DefaultTableModel(
                new Object[]{"ID", "Usuário", "Item", "Valor (R$)", "Data da Multa"},
                0 
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tabelaMultas = new JTable(tabelaModel);
        tabelaMultas.setPreferredScrollableViewportSize(new Dimension(600, 300));

        btnRegistrarPagamento = new JButton("Registrar Pagamento");

        btnRegistrarPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegistrarPagamento();
            }
        });
    }

    /**
     * Configura o layout do painel.
     */
    private void configurarLayout() {
        setLayout(new BorderLayout());

        JScrollPane scroll = new JScrollPane(tabelaMultas);

        JPanel painelInferior = new JPanel();
        painelInferior.add(btnRegistrarPagamento);

        add(scroll, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    /**
     * Carrega na JTable todas as multas pendentes obtidas do controller.
     */
    public void carregarDadosTabela() {
        tabelaModel.setRowCount(0); 

        List<Multa> pendentes = multaController.listarMultasPendentes();
        for (Multa multa : pendentes) {
            Object[] linha = new Object[]{
                    multa.getId(),
                    multa.getUsuario() != null ? multa.getUsuario().getNome() : "(sem usuário)",
                    multa.getEmprestimo() != null && multa.getEmprestimo().getItem() != null
                            ? multa.getEmprestimo().getItem().getTitulo()
                            : "(sem item)",
                    String.format("%.2f", multa.getValor()),
                    multa.getDataMulta() != null ? multa.getDataMulta().format(formatoData) : ""
            };
            tabelaModel.addRow(linha);
        }
    }

    /**
     * Ação do botão "Registrar Pagamento".
     *
     * Pede o ID da multa via diálogo, chama o controller para registrar o pagamento
     * e recarrega a tabela.
     */
    private void onRegistrarPagamento() {
        String input = JOptionPane.showInputDialog(
                this,
                "Informe o ID da multa a ser paga:",
                "Registrar Pagamento",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input == null) {
            return; 
        }

        input = input.trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Você precisa informar um ID.",
                    "ID inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idMulta;
        try {
            idMulta = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "O ID deve ser um número inteiro.",
                    "Formato inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
       
        multaController.registrarPagamento(idMulta);

        carregarDadosTabela();
    }
}