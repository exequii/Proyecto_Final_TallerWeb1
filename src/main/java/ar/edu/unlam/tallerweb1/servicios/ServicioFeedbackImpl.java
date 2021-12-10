package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosFeedback;
import ar.edu.unlam.tallerweb1.controladores.DatosReview;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCerveceriaException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCervezaException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaComentarioException;
import ar.edu.unlam.tallerweb1.excepciones.FaltaPuntuacionException;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCerveceria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service("servicioFeedback")
@Transactional
public class ServicioFeedbackImpl implements ServicioFeedback{

    private RepositorioFeedback repositorioFeedback;
    private RepositorioCerveceria repositorioCerveceria;

    @Autowired
    public ServicioFeedbackImpl(RepositorioFeedback repositorioFeedback,RepositorioCerveceria repositorioCerveceria) {
        this.repositorioFeedback = repositorioFeedback;
        this.repositorioCerveceria = repositorioCerveceria;
    }



    //ESTE ES EL QUE VA
    @Override
    public Feedback guardarFeedback(DatosFeedback feedback){
        if (feedback.getCerveceria() == null)
            throw new FaltaCerveceriaException();
        if (feedback.getCerveza() == null)
            throw new FaltaCervezaException();
        if (feedback.getPuntuacion() == null)
            throw new FaltaPuntuacionException();
        if (feedback.getComentario() == null)
            throw new FaltaComentarioException();
        Feedback nuevo = new Feedback();
        nuevo.setCerveceria(repositorioCerveceria.buscarCerveceriaPorNombre(feedback.getCerveceria()));
        nuevo.setPuntuacion(feedback.getPuntuacion());
        nuevo.setCerveza(feedback.getCerveza());
        nuevo.setComentario(feedback.getComentario());
        nuevo.setUsuario(new Usuario());
        repositorioFeedback.guardar(nuevo);
        return nuevo;
    }

    @Override
    public void guardarFeedback(Cerveceria cerveceria, Integer puntuacion, String cerveza, String comentario, Usuario usuario) {
        Feedback nuevo = new Feedback();
        nuevo.setCerveceria(cerveceria);
        nuevo.setPuntuacion(puntuacion);
        nuevo.setCerveza(cerveza);
        nuevo.setComentario(comentario);
        nuevo.setUsuario(usuario);
        repositorioFeedback.guardar(nuevo);
    }

    @Override
    public List<Feedback> filtrarPorPuntuacion(Integer puntuacion) {
        return repositorioFeedback.buscarFeedbacksPorPuntuacion(puntuacion);
    }

    @Override
    public List<DatosReview> generarListaReviews(List<Feedback> feedbacks) {
        List<DatosReview> reviews = new LinkedList<>();
        if (feedbacks.size() != 0) {
            for (Feedback feedback : feedbacks) {
                DatosReview review = new DatosReview();
                Long id = feedback.getId().longValue();

                review.setId(id);
                review.setUsuario(feedback.getUsuario());
                review.setCerveceria(feedback.getCerveceria().getNombre());
                review.setCerveza(feedback.getCerveza());
                review.setPuntuacion(feedback.getPuntuacion());
                review.setComentario(feedback.getComentario());

                reviews.add(review);
            }
        }
        return reviews;
    }



    @Override
    public List<Feedback> buscarTodosLosFeedbacks() {
        return repositorioFeedback.buscarFeedbacks();
    }

    @Override
    public void eliminarFeedback(Integer id) {
        Feedback buscado = consultarFeedback(id);
        buscado.setUsuario(null);
        buscado.setCerveceria(null);
        repositorioFeedback.eliminar(buscado);
    }

    @Override
    public Feedback consultarFeedback(Integer id) {
        return repositorioFeedback.buscarFeedbackPorId(id);
    }

    @Override
    public List<Feedback> buscarFeedbacksPorUsuario(String email){
        return repositorioFeedback.buscarFeedbacksPorMailDeUsuario(email);
    }

}
