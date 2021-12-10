package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosFeedback;
import ar.edu.unlam.tallerweb1.controladores.DatosReview;
import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioFeedback {


    void eliminarFeedback(Integer id);
    Feedback consultarFeedback(Integer id);
    List<Feedback> buscarTodosLosFeedbacks();
    List<Feedback> buscarFeedbacksPorUsuario(String email);
    Feedback guardarFeedback(DatosFeedback feedback);
    void guardarFeedback(Cerveceria cerveceria, Integer puntuacion, String cerveza, String comentario, Usuario usuario);
    List<Feedback> filtrarPorPuntuacion(Integer puntuacion);
    List<DatosReview> generarListaReviews(List<Feedback> feedbacks);
}
