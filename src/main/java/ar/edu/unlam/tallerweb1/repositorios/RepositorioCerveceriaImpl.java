package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cerveceria;
import ar.edu.unlam.tallerweb1.modelo.Cerveza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("repositorioCerveceria")
public class RepositorioCerveceriaImpl implements RepositorioCerveceria {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCerveceriaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardar(Cerveceria cerveceria) {
        sessionFactory.getCurrentSession().save(cerveceria);
    }

    @Override
    public void actualizar(Cerveceria cerveceria) {
        sessionFactory.getCurrentSession().update(cerveceria);
    }

    @Override
    public void eliminar(Cerveceria cerveceria) {
        sessionFactory.getCurrentSession().delete(cerveceria);
    }

    @Override
    public Cerveceria buscarCerveceriaPorNombre(String nombre) {
        final Session session = sessionFactory.getCurrentSession();
        return (Cerveceria) session.createCriteria(Cerveceria.class)
                .add(Restrictions.eq("nombre",nombre))
                .uniqueResult();
    }

    @Override
    public List buscarCerveceriaPorUsuario(String email) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cerveceria.class)
                .createAlias("propietario","i")
                .add(Restrictions.eq("i.email", email))
                .list();
    }

    @Override
    public List<Cerveceria> buscarCervecerias() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cerveceria.class).list();
    }

    @Override
    public List<Cerveceria> buscarCerveceriasPorBarrio(String barrio) {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Cerveceria>) session.createCriteria(Cerveceria.class)
                .add(Restrictions.like("barrio",barrio))
                .list();
    }

    @Override
    public List<Cerveza> buscarCervezasDeCerveceria(Cerveceria cerveceria) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cerveza.class)
                .createAlias("cervezas", "c")
                .add(Restrictions.like("c.nombre",cerveceria))
                .list();
    }

    public List<Cerveceria> buscarCerveceriaPorNombreDeCerveza(String nombreCerveza) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cerveceria.class)
                .createAlias("cervezas","i")
                .add(Restrictions.eq("i.nombre", nombreCerveza))
                .list();
    }

    @Override
    public Cerveceria buscarCerveceriaPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Cerveceria) session.createCriteria(Cerveceria.class)
                .add(Restrictions.eq("id",id))
                .uniqueResult();
    }

}
