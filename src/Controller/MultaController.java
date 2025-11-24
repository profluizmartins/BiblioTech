package Controller;

import Model.Usuario;

public class MultaController {
    public double calcularMulta(Usuario usuario, int diasAtraso) {
        double valor = diasAtraso * 2.50;
        return valor;
    }
}