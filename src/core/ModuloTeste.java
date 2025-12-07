package core;

import contracts.IModulo;
import javax.swing.*;
import java.awt.*;
//  classe provisória dos outros métodos
public class ModuloTeste implements IModulo {
    
    private String titulo;
    private Color corFundo;

    public ModuloTeste(String titulo, Color corFundo) {
        this.titulo = titulo;
        this.corFundo = corFundo;
    }

    @Override
    public JPanel getPanel() {
        JPanel p = new JPanel();
        p.setBackground(corFundo);
        p.add(new JLabel("Painel do Módulo: " + titulo));
        return p;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}
