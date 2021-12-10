package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cerveza;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.io.IOException;
import java.util.List;

public interface ServicioCerveceria {
    void guardarCerveceria(Cerveceria cerveceria);
    void guardarCerveceria(String nombre, String direccion, List<Cerveza> cervezas);
    void guardarCerveceria(String nombre, String direccion,String barrio, Usuario usuario);
    void eliminarCerveceria(Long id);
    void actualizar(Cerveceria cerveceria);

    List<Cerveceria> buscarCervecerias();
    List<Cerveceria> buscarCerveceriasPorUsuario(String email);

    Cerveceria buscarCerveceriaPorId(Long id);
    Cerveceria buscarCerveceriaPorNombre(String nombre);
    List<Cerveceria> buscarCerveceriaPorBarrio(String barrio);

    Double obtenerElPromedioDePuntuacionPorNombre(String nombre);
    List<Cerveceria> setearCerveceriaMejorPuntuada(List<Cerveceria> cervecerias);

    List<Cerveceria> filtrarCerveceriaPorCercania(Usuario usuario, String distanciaMaxima) throws IOException;


}
