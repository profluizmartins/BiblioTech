package view;

import controller.ReservaController;
import model.Reserva;
import exception.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Representa a Camada de Visão (View) do módulo de Reservas.
 * <p>
 * Responsável por desenhar a interface gráfica (GUI), capturar a entrada de dados
 * do usuário (Matrícula e ID do Item) e exibir os resultados (Tabela de Reservas
 * ou mensagens de erro/sucesso).
 * <p>
 */
public class PainelReserva extends JPanel {

    private JTextField txtMatriculaUsuario;
    private JTextField txtIdItem;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JButton btnAtender;
    private JTable tabelaReservas;
    private DefaultTableModel tableModel;

    /**
     * Controlador que intermedia as ações desta View com o Model.
     */
    private ReservaController controller;

    /**
     * Construtor padrão.
     * Inicializa os componentes visuais, mas não define o controlador imediatamente.
     */
    public PainelReserva() {
        inicializarComponentes();
    }

    /**
     * Injeção de Dependência do Controlador.
     * Permite que a View notifique o Controller sobre eventos (cliques de botão).
     * * @param controller A instância do controlador de reservas.
     */
    public void setController(ReservaController controller) {
        this.controller = controller;
        atualizarListagem();
    }

    /**
     * Configura o layout e adiciona os componentes (Botões, Campos, Tabela) ao painel.
     * Utiliza {@link BorderLayout} e {@link GridBagLayout} para organização.
     */
    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // --- Painel de Formulário (Topo) ---
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createTitledBorder("Nova Reserva"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo: Matrícula
        gbc.gridx = 0; gbc.gridy = 0;
        painelForm.add(new JLabel("Matrícula Usuário:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        txtMatriculaUsuario = new JTextField(15);
        painelForm.add(txtMatriculaUsuario, gbc);

        // Campo: ID Item
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelForm.add(new JLabel("ID Item (Acervo):"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        txtIdItem = new JTextField(15);
        painelForm.add(txtIdItem, gbc);

        // Botão Confirmar
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        btnConfirmar = new JButton("Confirmar Reserva");
        painelForm.add(btnConfirmar, gbc);

        add(painelForm, BorderLayout.NORTH);

        // --- Tabela de Listagem (Centro) ---
        String[] colunas = {"ID Reserva", "Item", "Usuário", "Data", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Tabela somente leitura
        };
        tabelaReservas = new JTable(tableModel);
        add(new JScrollPane(tabelaReservas), BorderLayout.CENTER);

        // --- Botões de Ação (Rodapé) ---
        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnAtender = new JButton("Atender Selecionada");
        btnCancelar = new JButton("Cancelar Selecionada");
        painelBotoes.add(btnAtender);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);

        configurarListeners();
    }

    /**
     * Define os ActionListeners (eventos de clique) para os botões.
     * Captura exceções lançadas pelo Controller e exibe mensagens apropriadas via JOptionPane.
     */
    private void configurarListeners() {
        // Listener do Botão Confirmar (Criar Nova Reserva)
        btnConfirmar.addActionListener(e -> {
            try {
                String matricula = txtMatriculaUsuario.getText();
                // Validação simples de formato na View
                int idItem = Integer.parseInt(txtIdItem.getText());

                // Chamada ao Controller
                String msg = controller.registrarReserva(matricula, idItem);

                // Feedback de Sucesso
                JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Limpeza e Atualização
                txtMatriculaUsuario.setText("");
                txtIdItem.setText("");
                atualizarListagem();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID do Item deve ser numérico.", "Erro Formato", JOptionPane.ERROR_MESSAGE);
            } catch (UsuarioNaoEncontradoException | ItemNaoEncontradoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Não Encontrado", JOptionPane.WARNING_MESSAGE);
            } catch (UsuarioSuspensoException | ItemEstaDisponivelException |
                     UsuarioJaPossuiReservaException | OperacaoNaoPermitidaException ex) {
                // Exceções de Regra de Negócio (Grupo 6)
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Bloqueio de Regra", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listeners para Cancelar e Atender (usam método auxiliar)
        btnCancelar.addActionListener(e -> processarAcao(id -> controller.cancelarReserva(id), "Reserva cancelada!"));
        btnAtender.addActionListener(e -> processarAcao(id -> controller.atenderReserva(id), "Reserva atendida!"));
    }

    /**
     * Interface funcional auxiliar para passar métodos do controller como parâmetro.
     */
    private interface AcaoConsumer { void accept(int id); }

    /**
     * Processa ações que dependem de uma linha selecionada na tabela.
     * Evita duplicação de código para os botões 'Atender' e 'Cancelar'.
     * * @param acao O método do controller a ser executado (ex: cancelarReserva).
     * @param msgSucesso A mensagem a ser exibida em caso de sucesso.
     */
    private void processarAcao(AcaoConsumer acao, String msgSucesso) {
        int linha = tabelaReservas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtém o ID da reserva da primeira coluna da tabela
        int idReserva = (int) tabelaReservas.getValueAt(linha, 0);

        try {
            acao.accept(idReserva);
            JOptionPane.showMessageDialog(this, msgSucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarListagem();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita ao Controller a lista de todas as reservas e atualiza a JTable.
     * Deve ser chamado sempre que houver uma alteração nos dados.
     */
    private void atualizarListagem() {
        if (controller == null) return;

        tableModel.setRowCount(0); // Limpa tabela
        List<Reserva> lista = controller.listarTodas();

        for (Reserva r : lista) {
            tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getItemAcervo().getTitulo(),
                    r.getUsuario().getNome(),
                    r.getDataReserva(),
                    r.getStatus()
            });
        }
    }

}
