package View;

import javax.swing.JPanel;

public interface IModulo {
    JPanel getPanel();
    default void onOpen() {}
    default void onClose() {}
}
