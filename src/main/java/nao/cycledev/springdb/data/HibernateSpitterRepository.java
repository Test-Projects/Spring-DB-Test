package nao.cycledev.springdb.data;

import nao.cycledev.springdb.model.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HibernateSpitterRepository implements SpitterRepository {

    SessionFactory sessionFactory;

    @Autowired
    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addSpitter(Spitter spitter) {
        currentSession().save(spitter);
    }

    @Override
    public Spitter findSpitter(long id) {
        return (Spitter)currentSession().get(Spitter.class, id);
    }
    
}
