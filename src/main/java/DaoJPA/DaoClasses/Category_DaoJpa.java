package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Category;
import DaoJPA.config.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Category_DaoJpa implements DaoJPA<Category> {

    private EntityManager entityManager;

    public Category_DaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Category_DaoJpa() {
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<Category> getById(int kategorienummer) {
        return Optional.ofNullable(entityManager.find(Category.class,kategorienummer));
    }

    @Override
    public List<Category> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM ausleihe.kategorie");
        return query.getResultList();
    }

    @Override
    public void save(Category category) {
        executeInsideTransaction(entityManager -> entityManager.persist(category));
    }

    @Override
    public void update(Category category) {
        //TODO hier nochmal auf eigenschaften eingehen (überprüfen) die nicht null sein dürfen !!!!!!
        executeInsideTransaction(entityManager -> entityManager.merge(category));
    }

    @Override
    public void delete(Category category) {
        executeInsideTransaction(entityManager -> entityManager.remove(category));
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
