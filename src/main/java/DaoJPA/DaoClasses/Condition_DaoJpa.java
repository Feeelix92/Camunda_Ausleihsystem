package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Condition;
import DaoJPA.config.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Condition_DaoJpa implements DaoJPA<Condition> {

    private EntityManager entityManager;

    public Condition_DaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Condition_DaoJpa(){
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<Condition> getById(int zustandId) {
        return Optional.ofNullable(entityManager.find(Condition.class,(short)zustandId));
    }

    @Override
    public List<Condition> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Condition> cr = cb.createQuery(Condition.class);
        Root<Condition> root = cr.from(Condition.class);
        cr.select(root);

        Query query = entityManager.createQuery(cr);
        List<Condition> conditionList = query.getResultList();

        return conditionList;
    }

    @Override
    public void save(Condition condition) {
        executeInsideTransaction(entityManager -> entityManager.persist(condition));
    }

    @Override
    public void update(Condition condition) {

    }

    @Override
    public void delete(Condition condition) {

    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        final EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
