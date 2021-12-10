package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosReserva;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReserva;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ServicioReservaTest {

    private RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private RepositorioCerveceria repositorioCerveceria = mock(RepositorioCerveceria.class);
    private ServicioReserva servicioReserva = new ServicioReservaImpl(repositorioReserva,repositorioCerveceria,repositorioUsuario);

    @Test(expected = Exception.class)
    public void alIntentarHacerUnaReservaNoHayDisponibilidad() throws Exception{
        DatosReserva reserva = givenTengoDatosReservaYUnaCerveceria();
        whenIntentoRealizarLaReservaMeDaError(reserva);
        thenNoSePudoRealizarLaReserva();
    }

    @Test
    public void seEnviaUnMailCorrectamente(){
        String email = "ezequiel.sanson@hotmail.com";
        Cerveceria antares = new Cerveceria("Antares");
        Integer cantidadPersonas = 3;
        Integer horario = 15;

        Boolean respuesta = whenRealizoUnMail(email,antares,cantidadPersonas,horario);

        thenSeRealizoElEnvioCorrectamente(respuesta);
    }


    /*************************** GIVEN *****************************/

    private DatosReserva givenTengoDatosReservaYUnaCerveceria() {
        Cerveceria cerveceria = new Cerveceria();
        cerveceria.setNombre("antares");
        cerveceria.setDireccion("palermo");
        repositorioCerveceria.guardar(cerveceria);

        DatosReserva reserva = new DatosReserva();
        reserva.setCerveceria("antares");
        reserva.setCantidadPersonas(31);
        reserva.setHorario(13);

        return reserva;
    }

    /*************************** WHEN ******************************/

    private void whenIntentoRealizarLaReservaMeDaError(DatosReserva reserva) throws Exception{
        servicioReserva.guardarReserva(reserva.getCerveceria(),reserva.getCantidadPersonas(),reserva.getHorario(),new Usuario());
    }

    private Boolean whenRealizoUnMail(String email, Cerveceria antares, Integer cantidadPersonas, Integer horario) {
        Boolean respuesta = servicioReserva.enviarConGMailTest(email,antares,cantidadPersonas,horario);
        return respuesta;
    }

    /*************************** THEN ******************************/

    private void thenNoSePudoRealizarLaReserva() {
        verify(repositorioReserva, never()).guardar(any());
    }

    private void thenSeRealizoElEnvioCorrectamente(Boolean respuesta) {
        assertThat(respuesta).isEqualTo(true);
    }

}
