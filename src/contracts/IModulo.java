package contracts;

import javax.swing.JPanel;

public interface IModulo {
    // Retorna o painel principal do módulo
    JPanel getPainel();
    
    // Retorna o nome do módulo
    String getTitulo();
}
