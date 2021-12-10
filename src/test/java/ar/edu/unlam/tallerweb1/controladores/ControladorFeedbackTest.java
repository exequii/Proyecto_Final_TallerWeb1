package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCerveceriaException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCervezaException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaComentarioException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaPuntuacionException;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCerveceria;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.mockito.Mockito.*;

public class ControladorFeedbackTest {

    private ServicioFeedback servicioFeedback = mock(ServicioFeedback.class);
    private ServicioCerveceria servicioCerveceria = mock(ServicioCerveceria.class);
    private ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
    private FeedbackController controladorFeedback = new FeedbackController(servicioFeedback,servicioCerveceria,servicioUsuario);

    private ModelAndView model;

    private static final DatosFeedback FEEDBACK
            = new DatosFeedback("Antares",3, "honey","Medio pelo", new Usuario());
    private static final Usuario USUARIO
            = new Usuario();
    @Test
    public void puedoDarUnFeedback() {
        givenTengoUnFeedback();
        ModelAndView model = whenEnvioElFeedback(FEEDBACK);
        thenElFeedbackSeEnviaConExito(model);
    }

    @Test
    public void noPuedaDarUnFeedbackSinCerveceria() {
        DatosFeedback feedbackIncorrecto = givenTengoUnFeedbackSinCerveceria();
        whenEnvioElFeedbackSinCerveceria(feedbackIncorrecto);
        thenElFeedbackDaError("Falta cerveceria");
    }
    @Test
    public void noPuedaDarUnFeedbackSinCerveza() {
        DatosFeedback feedbackIncorrecto = givenTengoUnFeedbackSinCerveza();
        whenEnvioElFeedbackSinCerveza(feedbackIncorrecto);
        thenElFeedbackDaError("Falta cerveza");
    }
    @Test
    public void noPuedaDarUnFeedbackSinPuntuacion() {
        DatosFeedback feedbackIncorrecto = givenTengoUnFeedbackSinPuntuacion();
        whenEnvioElFeedbackSinPuntuacion(feedbackIncorrecto);
        thenElFeedbackDaError("Falta puntuacion");
    }
    @Test
    public void noPuedaDarUnFeedbackSinComentario() {
        DatosFeedback feedbackIncorrecto = givenTengoUnFeedbackSinComentario();
        whenEnvioElFeedbackSinComentario(feedbackIncorrecto);
        thenElFeedbackDaError("Falta comentario");
    }




    /******************************* THEN ******************************/

    private void thenElFeedbackSeEnviaConExito(ModelAndView model) {
        assertThat(model.getViewName()).isEqualTo("home");
        assertThat(model.getModel().get("msg")).isEqualTo("Feedback guardado correctamente");
        assertThat(model.getModel().get("cerveceria")).isEqualTo(FEEDBACK.getCerveceria());
        assertThat(model.getModel().get("puntuacion")).isEqualTo(FEEDBACK.getPuntuacion());
        assertThat(model.getModel().get("cerveza")).isEqualTo(FEEDBACK.getCerveza());
        assertThat(model.getModel().get("comentario")).isEqualTo(FEEDBACK.getComentario());
    }
    private void thenElFeedbackDaError(String msg) {
        assertThat(model.getModel().get("msg")).isEqualTo(msg);
    }

    /******************************* WHEN ******************************/

    private ModelAndView whenEnvioElFeedback(DatosFeedback feedback) {
        return controladorFeedback.registrarFeedbackParaTest(feedback);
    }
    private void whenEnvioElFeedbackSinCerveceria(DatosFeedback feedbackIncorrecto) {
        when(servicioFeedback.guardarFeedback(feedbackIncorrecto)).thenThrow(FaltaCerveceriaException.class);
        model = controladorFeedback.registrarFeedbackParaTest(feedbackIncorrecto);
    }

    private void whenEnvioElFeedbackSinCerveza(DatosFeedback feedbackIncorrecto) {
        when(servicioFeedback.guardarFeedback(feedbackIncorrecto)).thenThrow(FaltaCervezaException.class);
        model = controladorFeedback.registrarFeedbackParaTest(feedbackIncorrecto);
    }

    private void whenEnvioElFeedbackSinPuntuacion(DatosFeedback feedbackIncorrecto) {
        when(servicioFeedback.guardarFeedback(feedbackIncorrecto)).thenThrow(FaltaPuntuacionException.class);
        model = controladorFeedback.registrarFeedbackParaTest(feedbackIncorrecto);
    }
    private void whenEnvioElFeedbackSinComentario(DatosFeedback feedbackIncorrecto) {
        when(servicioFeedback.guardarFeedback(feedbackIncorrecto)).thenThrow(FaltaComentarioException.class);
        model = controladorFeedback.registrarFeedbackParaTest(feedbackIncorrecto);
    }


    /******************************* GIVEN ******************************/

    private void givenTengoUnFeedback() {
    }
    private DatosFeedback givenTengoUnFeedbackSinCerveceria() {
        DatosFeedback feedback = new DatosFeedback();
        feedback.setComentario("Muy buena");
        feedback.setCerveza("Honey");
        feedback.setPuntuacion(4);
        return feedback;
    }

    private DatosFeedback givenTengoUnFeedbackSinCerveza() {
        DatosFeedback feedback = new DatosFeedback();
        feedback.setComentario("Muy buena");
        feedback.setCerveceria("Temple Bar");
        feedback.setPuntuacion(4);
        return feedback;
    }
    private DatosFeedback givenTengoUnFeedbackSinPuntuacion() {
        DatosFeedback feedback = new DatosFeedback();
        feedback.setCerveza("IPA");
        feedback.setComentario("Muy buena");
        feedback.setCerveceria("Temple Bar");
        return feedback;
    }
    private DatosFeedback givenTengoUnFeedbackSinComentario() {
        DatosFeedback feedback = new DatosFeedback();
        feedback.setCerveza("IPA");
        feedback.setCerveceria("Temple Bar");
        feedback.setPuntuacion(4);
        return feedback;
    }
}
