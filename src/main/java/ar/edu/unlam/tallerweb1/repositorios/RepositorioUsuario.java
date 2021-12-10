package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cupon;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);
	void guardar(Usuario usuario);
    Usuario buscar(String email);
	void modificar(Usuario usuario);
    List<Usuario> buscarUsuarioPorRol(String rol);
    List<Usuario> buscarUsuarioConMailLike(String mail);
    void actualizar(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void actualizarBarrio(Usuario usuario);

    List<Cupon> buscarCuponesPorUsuario(String email);

    void guardarCupon(Cupon cupon);

    void guardarNotificacion(Notificacion notificacion);

    List<Notificacion> buscarNotificacionesPorUsuario(String email);

    Notificacion buscarNotificacionporId(Long id);

    void actualizarNotificacion(Notificacion notiBuscada);
}
