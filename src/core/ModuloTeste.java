package core;
import contracts.IModulo;
import javax.swing.*;
import java.awt.*;
public class ModuloTeste implements IModulo{
    private String titulo;
    private Color corFundo;
        public ModuloTeste(String titulo, Color corFundo){
            this.titulo = titulo;
            this.corFundo = corFundo;
        }
        @Override
        public JPanel getPainel(){
            JPanel p = new JPanel();
            p.setBackground(corFundo);
            p.add(new JLabel("Módulo: " + titulo));
            return p;
        }
        @Override
        public String getTitulo(){
            return titulo;
        }
}
