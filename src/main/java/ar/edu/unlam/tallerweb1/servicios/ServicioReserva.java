package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Disponibilidad;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


import javax.mail.MessagingException;
import java.util.List;

public interface ServicioReserva {

    void eliminarReserva(Long id, Usuario usuario, Cerveceria cerveceria);
    void guardarReserva(String cerveceria, Integer cantidadPersonas, Integer horario) throws Exception;
    Disponibilidad guardarReserva(String cerveceria, Integer cantidadPersonas, Integer horario, Usuario usuario);
    List<Reserva> consultarReservas();
    List<Reserva> consultarReservasPorUsuario(String email);
    void enviarConGMail(String email, Cerveceria cerveceria,Integer cantidadPersonas, Integer horario);
    Boolean enviarConGMailTest(String email, Cerveceria cerveceria,Integer cantidadPersonas, Integer horario);
    List<Reserva> filtrarReservasActivas(List<Reserva> reservas);
}
