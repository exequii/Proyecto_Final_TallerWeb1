package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
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

public class RepositorioUsuarioConFeedbacksTest extends SpringTest {

    private static final Usuario USUARIO
            = new Usuario("Eze@eze.com","asdasd","usuario",true, new Cuenta());

    @Autowired
    private RepositorioFeedback repositorioFeedback;

    @Test
    @Rollback @Transactional
    public void unUsuarioTieneMuchosFeedbacks(){
        givenTengoUnUsuarioConFeedbacks(3, USUARIO);
        List<Feedback> feedbacks = whenBuscoFeedbacksDelUsuario("Eze@eze.com");
        thenEncuentro(feedbacks,3);

    }

    /********************************** GIVEN ***********************************/

    private void givenTengoUnUsuarioConFeedbacks(int cantidadDeFeedbacks, Usuario USUARIO) {
            Usuario usuario = new Usuario();
            usuario.setEmail(USUARIO.getEmail());
            usuario.setPassword(USUARIO.getPassword());
            usuario.setRol(USUARIO.getRol());
            usuario.setActivo(USUARIO.getActivo());


        for(int i = 0; i < cantidadDeFeedbacks; i++){
            Feedback feedback = new Feedback();
            feedback.setPuntuacion(i);
            feedback.setComentario("Me parecio re piola " + i + ".");
            feedback.setCerveza("Honey " + i + ".");
            feedback.setCerveceria(new Cerveceria("Antares " + i + "."));
            feedback.setFecha(new Date());

            feedback.setUsuario(usuario);

            session().save(feedback);
        }
    }

    /********************************* WHEN **************************************/

    private List<Feedback> whenBuscoFeedbacksDelUsuario(String email) {
        return repositorioFeedback.buscarFeedbacksPorMailDeUsuario(email);
    }

    /********************************** THEN *************************************/

    private void thenEncuentro(List<Feedback> feedbacks, int cantidadDeFeedbacks) {
        assertThat(feedbacks).hasSize(cantidadDeFeedbacks);
    }
}
