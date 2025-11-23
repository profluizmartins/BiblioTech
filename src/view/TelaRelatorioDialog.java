package view;
import javax.swing.*;
import java.awt.*;


public class TelaRelatorioDialog extends JDialog {

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