package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Feedback;

import java.util.List;

public interface RepositorioFeedback {
    //void guardar(DatosFeedback feedback);
    void guardar(Feedback feedback);
    void eliminar(Feedback feedback);
    List<Feedback> buscarFeedbacks();
    Feedback buscarFeedbackPorId(Integer id);
    List<Feedback> buscarFeedbacksPorPuntuacion(Integer puntuacion);
    List<Feedback> buscarFeedbacksPorMailDeUsuario(String email);
    List<Feedback> buscarFeedbacksPorCerveceria(String cerveceria);
    List<Feedback> buscarFeedbacksPorCerveza(String cerveza);

}
