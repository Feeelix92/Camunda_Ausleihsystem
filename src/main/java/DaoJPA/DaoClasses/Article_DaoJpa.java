package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.Article;
import DaoJPA.config.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Article_DaoJpa implements DaoJPA<Article> {

    private EntityManager entityManager;

    public Article_DaoJpa(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Article_DaoJpa() {
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<Article> getById(int artikelnummer) {
        return Optional.ofNullable(entityManager.find(Article.class,artikelnummer));
    }

    @Override
    public List<Article> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM ausleihe.artikel");
        return  query.getResultList();
    }

    @Override
    public void save(Article article) {
        executeInsideTransaction(entityManager -> entityManager.persist(article));
    }

    @Override
    public void update(Article article) {
        //TODO hier nochmal auf eigenschaften eingehen (überprüfen) die nicht null sein dürfen !!!!!!
        executeInsideTransaction(entityManager -> entityManager.merge(article));
    }

    @Override
    public void delete(Article article) {
        executeInsideTransaction(entityManager -> entityManager.remove(article));
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
