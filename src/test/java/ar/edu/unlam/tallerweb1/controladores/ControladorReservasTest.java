package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorReservasTest{

    private ServicioReserva servicioReserva = mock(ServicioReserva.class);
    private ServicioCerveceria servicioCerveceria = mock(ServicioCerveceria.class);
    private ReservaController reservaController = new ReservaController(servicioReserva, servicioCerveceria);
    private static final DatosReserva RESERVA
            = new DatosReserva(31,4,"antares");

    @Test
    public void puedoHacerUnaReserva(){
        DatosReserva reserva = givenQuieroHacerUnaReserva(13,4,"antares");
        ModelAndView model = whenLaReservaSeEnvia(reserva);
        thenLaReservaSeGuardoCorrectamente(model,reserva);
    }

    /*********************************** GIVEN *******************************/

    private DatosReserva givenQuieroHacerUnaReserva(Integer horario, Integer cantidadPersonas, String cerveceria) {
        DatosReserva reserva = new DatosReserva();
        reserva.setHorario(horario);
        reserva.setCantidadPersonas(cantidadPersonas);
        reserva.setCerveceria(cerveceria);
        return reserva;
    }
/*
    private void givenLaReservaExcedeLaCapacidad(DatosReserva RESERVA) throws Exception {
        doThrow(Exception.class).when(servicioReserva).guardarReserva(RESERVA.getCerveceria(),RESERVA.getCantidadPersonas(),RESERVA.getHorario(), new Usuario());
    }
*/
    /*********************************** WHEN ********************************/

    private ModelAndView whenLaReservaSeEnvia(DatosReserva reserva){
        ModelAndView model = reservaController.registrarReservaParaTest(reserva);
        return model;
    }

    /*********************************** THEN *******************************/

    private void thenLaReservaSeGuardoCorrectamente(ModelAndView model,DatosReserva reserva) {
        Assertions.assertThat(model.getViewName()).isEqualTo("home");
        Assertions.assertThat(model.getModel().get("msg")).isEqualTo("Reserva guardada correctamente");
        Assertions.assertThat(model.getModel().get("cerveceria")).isEqualTo(reserva.getCerveceria());
        Assertions.assertThat(model.getModel().get("cantidad")).isEqualTo(reserva.getCantidadPersonas());
        Assertions.assertThat(model.getModel().get("horario")).isEqualTo(reserva.getHorario());
    }
}
