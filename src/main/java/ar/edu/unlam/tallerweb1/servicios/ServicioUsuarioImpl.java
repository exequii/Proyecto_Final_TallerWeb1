package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cupon;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{
    RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Cupon> buscarCuponesPorUsuario(String email) {
        return repositorioUsuario.buscarCuponesPorUsuario(email);
    }

    @Override
    public void guardarCupon(Cupon cupon) {
        repositorioUsuario.guardarCupon(cupon);
    }

    @Override
    public void guardarNotificacion(Notificacion notificacion) {
        repositorioUsuario.guardarNotificacion(notificacion);
    }

    @Override
    public List<Notificacion> buscarNotificaciones(String email) {
        List<Notificacion> notificacionesNoLeidas = new LinkedList<>();
        List<Notificacion> notificaciones = repositorioUsuario.buscarNotificacionesPorUsuario(email);
        for (Notificacion notificacion : notificaciones) {
            if (notificacion.getLeida()==false) {
                notificacionesNoLeidas.add(notificacion);
            }
        }
        return notificacionesNoLeidas;
    }

    @Override
    public void actualizarEstadoNotificacion(Long id) {
        Notificacion notiBuscada = repositorioUsuario.buscarNotificacionporId(id);
        notiBuscada.setLeida(true);
        repositorioUsuario.actualizarNotificacion(notiBuscada);
    }
}
