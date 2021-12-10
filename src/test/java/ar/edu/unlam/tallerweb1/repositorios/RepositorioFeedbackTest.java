package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosFeedback;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RepositorioFeedbackTest extends SpringTest {

    @Autowired
    private RepositorioFeedback repositorioFeedback;

    /*
    @Test
    @Rollback @Transactional
    public void buscarFeedbackPorId(){
        givenExistenFeedbacksConId(3);
        List<Feedback> feedbacks = whenBuscoFeedbacksPorId(3);
        thenEncuentro(feedbacks,1);
    }
    */

    @Test
    @Rollback @Transactional
    public void buscarTodosLosFeedbacksCreados(){
        givenExistenFeedbacksConId(3);
        List<Feedback> feedbacks = whenBuscoFeedbacks();
        thenEncuentro(feedbacks,3);
    }


    @Test
    @Rollback @Transactional
    public void buscarFeedbackPorPuntuacion(){
        givenExistenFeedbacks();
        List<Feedback> feedbacks = whenBuscoFeedbacksPorPuntuacion(5);
        thenEncuentro(feedbacks,2);
    }

    @Test
    @Rollback @Transactional
    public void buscarFeedbackporCerveceria() {
        givenExistenFeedbacks();
        List<Feedback> reviewsPorCervecerias = whenBuscoLasReviewsPorCervecerias("Antares");
        thenEncuentro(reviewsPorCervecerias,3);

    }
    @Test
    @Rollback @Transactional
    public void buscarFeedbackporCerveza() {
        givenExistenFeedbacks();
        List<Feedback> reviewsPorCervezas = whenBuscoLasReviewsPorCervezas("IPA");
        thenEncuentro(reviewsPorCervezas,2);

    }

    /******************************* GIVEN ******************************/

    private void givenExistenFeedbacksConId(Integer cantidadFeedbacks) {
        for(int i = 0; i < cantidadFeedbacks; i++){
            Feedback feedback = new Feedback();
            feedback.setPuntuacion(i);
            feedback.setComentario("Mi puntuacion es: "+i+" ya que me parecio re piola.");
            feedback.setFecha(new Date());

            Usuario usuario = new Usuario();
            usuario.setEmail("usuario-"+i+"@usuario.com");
            usuario.setPassword("123"+i);
            usuario.setRol("User");

            Cuenta cuenta = new Cuenta();
            cuenta.setCreada(new Date());
            usuario.setCuenta(cuenta);

            feedback.setUsuario(usuario);

            session().save(feedback);
            //me trae desde el springTest una session, que es la que me permite guardar algo en la bdd
        }

    }
    private void givenExistenFeedbacks() {
        Feedback feedbackUno= new Feedback(3, "medio pelo", new Cerveceria("Antares"),"IPA", new Usuario());
        Feedback feedbackDos= new Feedback(5, "medio pelo", new Cerveceria("Antares"),"honey", new Usuario());
        Feedback feedbackTres= new Feedback(5, "medio pelo", new Cerveceria("Antares"),"negra", new Usuario());
        Feedback feedbackCuatro= new Feedback(4, "medio pelo", new Cerveceria("La birra bar"),"IPA", new Usuario());
        session().save(feedbackUno);
        session().save(feedbackDos);
        session().save(feedbackTres);
        session().save(feedbackCuatro);

    }

    /********************************* WHEN ********************************/

    private List<Feedback> whenBuscoFeedbacks() {
        return repositorioFeedback.buscarFeedbacks();
    }

    private List<Feedback> whenBuscoFeedbacksPorPuntuacion(Integer puntuacion) {
        return repositorioFeedback.buscarFeedbacksPorPuntuacion(puntuacion);
    }
    private List<Feedback> whenBuscoLasReviewsPorCervecerias(String parametroBuscado) {
        return repositorioFeedback.buscarFeedbacksPorCerveceria(parametroBuscado);
    }
    private List<Feedback> whenBuscoLasReviewsPorCervezas(String cerveza) {
        return repositorioFeedback.buscarFeedbacksPorCerveza(cerveza);
    }


    /******************************** THEN **********************************/

    private void thenEncuentro(List<Feedback> feedbacks, int feedbacksEncontrados) {
        assertThat(feedbacks).hasSize(feedbacksEncontrados);
    }

}
