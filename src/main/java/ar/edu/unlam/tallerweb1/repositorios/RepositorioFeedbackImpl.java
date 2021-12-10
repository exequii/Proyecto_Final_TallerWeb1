package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;


@Repository("repositorioFeedback")
public class RepositorioFeedbackImpl implements RepositorioFeedback {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioFeedbackImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

   /*
    @Override
    public void guardar(DatosFeedback feedback) {
        sessionFactory.getCurrentSession().save(feedback);
    }
*/

    // ESTE ES EL QUE VA
    @Override
    public void guardar(Feedback feedback) {
        sessionFactory.getCurrentSession().save(feedback);
    }

    @Override
    public void eliminar(Feedback feedback) {
        sessionFactory.getCurrentSession().remove(feedback);
    }

    @Override
    public List<Feedback> buscarFeedbacks() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Feedback.class).list();
    }

    @Override
    public Feedback buscarFeedbackPorId(Integer id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Feedback) session.createCriteria(Feedback.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public List<Feedback> buscarFeedbacksPorPuntuacion(Integer puntuacion) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Feedback.class)
                .add(Restrictions.like("puntuacion", puntuacion))
                .list();
    }

    @Override
    public List<Feedback> buscarFeedbacksPorMailDeUsuario(String email) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Feedback.class)
                .createAlias("usuario","i")
                .add(Restrictions.eq("i.email", email))
                .list();
    }

    @Override
    public List<Feedback> buscarFeedbacksPorCerveceria(String cerveceria) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Feedback.class)
                .createAlias("cerveceria","c")
                .add(Restrictions.like("c.nombre", cerveceria))
                .list();
    }

    @Override
    public List<Feedback> buscarFeedbacksPorCerveza(String cerveza) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Feedback.class)
                .add(Restrictions.like("cerveza", cerveza))
                .list();
    }
}
