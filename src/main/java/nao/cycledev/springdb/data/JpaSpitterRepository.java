package nao.cycledev.springdb.data;

import nao.cycledev.springdb.model.Spitter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addSpitter(Spitter spitter) {
        em.persist(spitter);
    }

    @Override
    public Spitter findSpitter(long id) {
        return em.find(Spitter.class, id);
    }
}
