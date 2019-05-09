package ar.edu.unlam.tallerweb1.dao;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pais;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class PaisDaoImpl implements PaisDao {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<Pais> getAllPais() {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Pais.class);
        List<Pais> results = criteria.list();
        return results;
    }

    @Override
    public void savePais(Pais pais) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(pais);
    }

    @Override
    public Pais getPais(int id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Pais.class, id);
    }

    @Override
    public void removePais(int id) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(getPais(id));
    }
}
