package Controller;

import model.MockUsuario;

public class MockMultaController {
    public double calcularMulta(MockUsuario mockUsuario, int diasAtraso) {
        double valor = diasAtraso * 2.50;
        return valor;
    }
}