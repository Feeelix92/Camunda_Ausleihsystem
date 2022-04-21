package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.BorrowDetails;
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

public class BorrowDetails_DaoJpa implements DaoJPA<BorrowDetails> {

    private EntityManager entityManager;

    public BorrowDetails_DaoJpa(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public BorrowDetails_DaoJpa(){
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<BorrowDetails> getById(int ausleihnummer) {
        return Optional.ofNullable(entityManager.find(BorrowDetails.class,ausleihnummer));
    }

    @Override
    public List<BorrowDetails> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowDetails> cr = cb.createQuery(BorrowDetails.class);
        Root<BorrowDetails> root = cr.from(BorrowDetails.class);
        cr.select(root);

        Query query = entityManager.createQuery(cr);
        List<BorrowDetails> borrowDetailsList = query.getResultList();

        return borrowDetailsList;
    }

    @Override
    public void save(BorrowDetails borrowDetails) {
        executeInsideTransaction(entityManager -> entityManager.persist(borrowDetails));
    }

    @Override
    public void update(BorrowDetails borrowDetails) {

    }

    @Override
    public void delete(BorrowDetails borrowDetails) {

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
