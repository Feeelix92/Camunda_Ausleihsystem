package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Borrow;
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

public class Borrow_DaoJpa implements DaoJPA<Borrow> {

    private EntityManager entityManager;

    public Borrow_DaoJpa(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Borrow_DaoJpa(){
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<Borrow> getById(int ausleihnummer) {
        return Optional.ofNullable(entityManager.find(Borrow.class,ausleihnummer));
    }

    @Override
    public List<Borrow> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Borrow> cr = cb.createQuery(Borrow.class);
        Root<Borrow> root = cr.from(Borrow.class);
        cr.select(root);

        Query query = entityManager.createQuery(cr);
        List<Borrow> borrowList = query.getResultList();

        return borrowList;
    }

    @Override
    public void save(Borrow borrow) {
        executeInsideTransaction(entityManager -> entityManager.persist(borrow));
    }

    @Override
    public void update(Borrow borrow) {

    }

    @Override
    public void delete(Borrow borrow) {

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
