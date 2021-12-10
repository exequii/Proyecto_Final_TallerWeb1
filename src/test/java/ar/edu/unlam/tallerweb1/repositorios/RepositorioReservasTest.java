package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RepositorioReservasTest  extends SpringTest {

    @Autowired
    private RepositorioReserva repositorioReserva;

    @Test
    @Rollback
    @Transactional
    public void buscarTodasLasReservas(){
        givenExistenReservas(3);
        List<Reserva> reservas = whenBuscoReservas();
        thenEncuentro(reservas,3);
    }

    @Test
    @Rollback
    @Transactional
    public void buscarReservasPorCerveceria(){
        givenExistenReservas(3);
        List<Reserva> reservas = whenBuscoReservasPorCerveceria("antares");
        thenEncuentro(reservas,3);
    }

    @Test
    @Rollback
    @Transactional
    public void buscarReservasPorUsuario(){
        givenExistenReservas(3);
        List<Reserva> reservas = whenBuscoReservasPorUsuario("usuario@usuario.com");
        thenEncuentro(reservas,3);
    }


    /****************************************** GIVEN **********************************/

    private void givenExistenReservas(Integer cantidadReservas) {
        Cerveceria antares = new Cerveceria();
        antares.setNombre("antares");
        antares.setDireccion("palermo");

        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@usuario.com");
        usuario.setPassword("123");
        usuario.setRol("User");

        session().save(antares);
        session().save(usuario);

        for(int i = 0; i < cantidadReservas; i++){
            Reserva reserva = new Reserva();
            reserva.setCerveceria(antares);
            reserva.setCantidadPersonas(4);
            reserva.setHorario(13);
            reserva.setUsuario(usuario);

            session().save(reserva);
        }
    }

    /************************************* WHEN ***********************************/

    private List<Reserva> whenBuscoReservas() {
        return repositorioReserva.buscarReservas();
    }

    private List<Reserva> whenBuscoReservasPorCerveceria(String cerveceria) {
        return repositorioReserva.buscarReservasPorCerveceria(cerveceria);
    }

    private List<Reserva> whenBuscoReservasPorUsuario(String mailUsuario) {
        return repositorioReserva.buscarReservasPorUsuario(mailUsuario);
    }

    /************************************* THEN **************************************/

    private void thenEncuentro(List<Reserva> reservas, Integer cantidadReservas) {
        assertThat(reservas).hasSize(cantidadReservas);
    }


}
