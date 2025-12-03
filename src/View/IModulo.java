package View;

import javax.swing.JPanel;

/**
 * Interface que define o padrão básico para módulos visuais do sistema.
 * Cada módulo deve fornecer um painel principal e pode opcionalmente
 * implementar comportamentos ao abrir e ao fechar.
 *
 * @author Andrey Raphael Gomes Ribeiro Ferreira
 * @author Daniel Noleto de Oliveira
 * @author Uriel Fernades de Santos
 * @author Luiz Henrique Lima de Oliveira
 * @author Pedro Martins de Melo Ferreira
 * @version 1.0
 */
public interface IModulo {

    /**
     * Retorna o painel principal do módulo.
     *
     * @return JPanel correspondente ao módulo.
     */
    JPanel getPanel();

    /**
     * Método executado automaticamente quando o módulo é aberto.
     * Implementação opcional.
     */
    default void onOpen() {}

    /**
     * Método executado automaticamente quando o módulo é fechado.
     * Implementação opcional.
     */
    default void onClose() {}
}
