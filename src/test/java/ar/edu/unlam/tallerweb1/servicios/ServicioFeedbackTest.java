package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosFeedback;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFeedback;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioFeedbackTest {

    private static final Usuario USUARIO =
            new Usuario("nahue@nahue.com","asdasd","usuario",true, new Cuenta());
    public static final Feedback FEEDBACK =
            new Feedback(5,"buena",new Date(),new Cerveceria("Antares"),"IPA", USUARIO);
    public static final Feedback FEEDBACK2 =
            new Feedback(5,"buena",new Date(),new Cerveceria("La birra"),"IPA", USUARIO);
    public static final Feedback FEEDBACK3 =
            new Feedback(5,"buena",new Date(),new Cerveceria("Antares"),"IPA", USUARIO);

    private RepositorioFeedback repositorioFeedback = mock(RepositorioFeedback.class);
    private RepositorioCerveceria repositorioCerveceria = mock(RepositorioCerveceria.class);
    private ServicioFeedback servicioFeedback = new ServicioFeedbackImpl(repositorioFeedback,repositorioCerveceria);

    @Test
    public void deberiaGuardarUnFeedback() throws Exception{
        givenTengoUnFeedback(FEEDBACK);
        whenGuardo(FEEDBACK);
        thenSeGuardaExitosamente();
    }

    @Test
    public void deberiaTraerTodosLosFeedbacks(){
        givenTengoVariosFeedbackGuardados();
        whenBuscoTodosLosFeedbacks();
        thenSeTraeCorrectamente();
    }

    /********************************** GIVEN *******************************************/

    private void givenTengoUnFeedback(Feedback FEEDBACK) {
        assertThat(FEEDBACK).isNotNull();
    }

    private void givenTengoVariosFeedbackGuardados() {
        repositorioFeedback.guardar(FEEDBACK);
        repositorioFeedback.guardar(FEEDBACK2);
        repositorioFeedback.guardar(FEEDBACK3);

    }
    /********************************** WHEN *********************************************/

    private void whenGuardo(Feedback FEEDBACK) throws Exception {
        repositorioFeedback.guardar(FEEDBACK);
    }
    private void whenBuscoTodosLosFeedbacks() {
        servicioFeedback.buscarTodosLosFeedbacks();
    }

    /********************************** THEN *********************************************/

    private void thenSeGuardaExitosamente() {
        verify(repositorioFeedback, times(1)).guardar(any());
    }

    private void thenSeTraeCorrectamente() {
        verify(repositorioFeedback, times(1)).buscarFeedbacks();
    }

}
