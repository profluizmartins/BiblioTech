package Controller;

public class ReservaController {
    public boolean verificarReserva(int idItem) {
        return idItem == 50;
    }

    public String getProximoUsuario(int idItem) {
        return "Maria da Reserva";
    }
}