package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cupon;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioUsuario {
    List<Cupon> buscarCuponesPorUsuario(String email);
    void guardarCupon(Cupon cupon);

    void guardarNotificacion(Notificacion notificacion);

    List<Notificacion> buscarNotificaciones(String email);

    void actualizarEstadoNotificacion(Long id);
}
