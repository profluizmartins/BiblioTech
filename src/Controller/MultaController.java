package Controller;

public class MultaController {
    public double gerarMulta(Object usuario, long diasAtraso) {
        double valor = diasAtraso * 2.50;
        System.out.println("Grupo 7: Gerando multa de R$" + valor);
        return valor;
    }
}