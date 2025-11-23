package Controller;

import Model.Usuario;

public class MultaController {
    public double gerarMulta(Usuario usuario, int diasAtraso) {
        double valor = diasAtraso * 2.50;
        System.out.println("Grupo 7: Gerando multa de R$" + valor);
        return valor;
    }
}