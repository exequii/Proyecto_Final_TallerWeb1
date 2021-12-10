package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cerveza;

import java.util.List;

public interface RepositorioCerveceria {

    void guardar(Cerveceria cerveceria);
    void actualizar(Cerveceria cerveceria);
    void eliminar(Cerveceria cerveceria);

    Cerveceria buscarCerveceriaPorNombre(String nombre);
    List buscarCerveceriaPorUsuario(String email);
    List<Cerveceria> buscarCervecerias();
    List<Cerveceria> buscarCerveceriasPorBarrio(String barrio);
    List<Cerveza>buscarCervezasDeCerveceria(Cerveceria cerveceria);

    List<Cerveceria> buscarCerveceriaPorNombreDeCerveza(String nombreCerveza);

    Cerveceria buscarCerveceriaPorId(Long id);

}
