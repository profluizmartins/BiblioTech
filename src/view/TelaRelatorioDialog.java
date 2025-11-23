package view;
import javax.swing.*;
import java.awt.*;


/**
 * Uma janelinha simples pra exibir os textos dos relatórios.
 * É basicamente um pop-up com scroll pra mostrar os dados(os de agora são falsos)
 * sem precisar criar uma tela complexa pra cada tipo de relatório agora.
 */
public class TelaRelatorioDialog extends JDialog {

    /**
     * Monta a janela com o texto que a gente passar
     * * @param owner A janela principal (trava o foco nela enquanto estiver aberta).
     * @param titulo
     * @param conteudo O textozão do relatório pra jogar na tela
     */
    public TelaRelatorioDialog(Frame owner, String titulo, String conteudo) {
        super(owner,titulo,true);
        setSize(400,300);
        setLocationRelativeTo(owner);
        
        JTextArea areaTexto=new JTextArea(conteudo);
        areaTexto.setEditable(false);
        areaTexto.setMargin(new Insets(10,10,10,10));
        
        add(new JScrollPane(areaTexto));
    }
}