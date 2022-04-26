package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.ArticleType;
import DaoJPA.config.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ArticleType_DaoJpa implements DaoJPA<ArticleType> {

    private EntityManager entityManager;

    public ArticleType_DaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ArticleType_DaoJpa() {
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<ArticleType> getById(int articleTypId) {
        return Optional.ofNullable(entityManager.find(ArticleType.class, articleTypId));
    }

    @Override
    public List<ArticleType> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM ausleihe.artikeltyp");
        return query.getResultList();
    }

    @Override
    public void save(ArticleType articleType) {
        executeInsideTransaction(entityManager -> entityManager.persist(articleType));
    }

    @Override
    public void update(ArticleType articleType) {
        //TODO hier nochmal auf eigenschaften eingehen (überprüfen) die nicht null sein dürfen !!!!!!
        executeInsideTransaction(entityManager -> entityManager.merge(articleType));
    }

    @Override
    public void delete(ArticleType articleType) {
        executeInsideTransaction(entityManager -> entityManager.remove(articleType));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        final EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
