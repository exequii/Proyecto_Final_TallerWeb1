package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioFeedback;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorReviewTest {
    private ServicioFeedback servicioFeedback = mock(ServicioFeedback.class);
    private ReviewController controladorReview = new ReviewController(servicioFeedback);
    private static final DatosFeedback FEEDBACK1
            = new DatosFeedback("Antares", 5, "honey","Medio pelo", new Usuario());
    private static final DatosFeedback FEEDBACK2
            = new DatosFeedback("Antares", 5, "honey","Medio pelo", new Usuario());
    private static final Usuario USUARIO
            = new Usuario();
    private ModelAndView model;

    @Test
    public void puedoFiltarLasReviewPorPuntuacion() {
        givenExistenVariasReviews();
        ModelAndView model = whenFiltroPorPuntuacion(5);
        thenAccedeAlMetodoFiltrarYDevuelveLaVista(model);
    }

    /******************************* THEN ******************************/

    private void thenAccedeAlMetodoFiltrarYDevuelveLaVista(ModelAndView model) {
        assertThat(model.getViewName()).isEqualTo("review");
        verify(servicioFeedback, times(1)).filtrarPorPuntuacion(5);
    }

    /******************************* WHEN
     * @return*****************************/

    private ModelAndView whenFiltroPorPuntuacion(Integer puntuacion) {
        return controladorReview.reviewsFiltradas(5);
    }


    /******************************* GIVEN ******************************/

    private void givenExistenVariasReviews() {
        servicioFeedback.guardarFeedback(FEEDBACK1);
        servicioFeedback.guardarFeedback(FEEDBACK2);
    }
}
