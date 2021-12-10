package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.util.List;

public interface RepositorioReserva {

    void eliminarReserva(Reserva reserva);
    void guardar(Reserva reserva);
    List<Reserva> buscarReservas();
    List<Reserva> buscarReservasPorCerveceria(String nombre);
    Reserva buscarReservaPorId(Long id);
    void actualizarDatos(Reserva reserva);
    void actualizarMerge(Reserva reserva);

    List<Reserva> buscarReservasPorUsuario(String mailUsuario);
}
