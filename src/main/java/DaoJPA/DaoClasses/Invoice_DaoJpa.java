package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Invoice;
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

public class Invoice_DaoJpa implements DaoJPA<Invoice> {

    private EntityManager entityManager;

    public Invoice_DaoJpa(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Invoice_DaoJpa(){
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<Invoice> getById(int rechnungsnummer) {
        return Optional.ofNullable(entityManager.find(Invoice.class,rechnungsnummer));
    }

    @Override
    public List<Invoice> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Invoice> cr = cb.createQuery(Invoice.class);
        Root<Invoice> root = cr.from(Invoice.class);
        cr.select(root);

        Query query = entityManager.createQuery(cr);
        List<Invoice> invoiceList = query.getResultList();

        return invoiceList;
    }

    @Override
    public void save(Invoice invoice) {
        executeInsideTransaction(entityManager -> entityManager.persist(invoice));
    }

    @Override
    public void update(Invoice invoice) {

    }

    @Override
    public void delete(Invoice invoice) {

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
