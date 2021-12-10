package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Feedback;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioReserva")
public class RepositorioReservaImpl implements RepositorioReserva{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioReservaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        sessionFactory.getCurrentSession().delete(reserva);
    }

    @Override
    public void guardar(Reserva reserva) {
        sessionFactory.getCurrentSession().save(reserva);
    }

    @Override
    public List<Reserva> buscarReservas() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Reserva.class)
                .list();
    }

    @Override
    public List<Reserva> buscarReservasPorCerveceria(String nombre) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Reserva.class)
                .createAlias("cerveceria","i")
                .add(Restrictions.eq("i.nombre", nombre))
                .list();
    }

    @Override
    public Reserva buscarReservaPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Reserva) session.createCriteria(Reserva.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void actualizarDatos(Reserva reserva){
        sessionFactory.getCurrentSession().update(reserva);
    }

    @Override
    public void actualizarMerge(Reserva reserva){
        sessionFactory.getCurrentSession().merge(reserva);
    }

    @Override
    public List<Reserva> buscarReservasPorUsuario(String mailUsuario) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Reserva.class)
                .createAlias("usuario","i")
                .add(Restrictions.eq("i.email", mailUsuario))
                .list();
    }

}
