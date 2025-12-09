package view;

import java.awt.*;
import javax.swing.*;

/**
 * Classe responsável pela interface gráfica utilizada para exibição dos relatórios
 * gerados pelos controllers. Esta tela é apresentada no formato de caixa de diálogo
 * modal, impedindo a interação com a janela principal enquanto estiver aberta.
 *
 * Seu objetivo é disponibilizar um espaço de leitura do relatório em formato textual,
 * utilizando um componente rolável a fim de facilitar visualização de conteúdos extensos.
 *
 * Esta classe pertence exclusivamente à camada View e não contém lógica de negócio.
 */
public class TelaRelatorioDialog extends JDialog {

    /**
     * Construtor responsável pela criação da janela de relatório. Define as
     * características visuais e insere um componente de texto de somente leitura.
     *
     * @param owner Janela que originou esta caixa de diálogo, utilizada para centralização.
     * @param titulo Título exibido na barra superior da janela.
     * @param conteudo Texto contendo o relatório gerado pelo sistema.
     */
    public TelaRelatorioDialog(Frame owner, String titulo, String conteudo) {
        super(owner, titulo, true); // "true" indica comportamento modal

        setSize(400, 300);
        setLocationRelativeTo(owner); // Centraliza a janela em relação ao elemento pai

        JTextArea areaTexto = new JTextArea(conteudo);
        areaTexto.setEditable(false);             // Impede alterações pelo usuário
        areaTexto.setLineWrap(true);              // Quebra de linha automática
        areaTexto.setWrapStyleWord(true);         // Mantém palavras inteiras ao quebrar linha
        areaTexto.setMargin(new Insets(10, 10, 10, 10)); // Espaçamento interno para melhor leitura

        JScrollPane painelRolagem = new JScrollPane(areaTexto);
        painelRolagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(painelRolagem);
    }
}