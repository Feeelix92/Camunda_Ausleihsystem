package DaoJPA.DaoClasses;

import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.User;
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

public class User_DaoJpa implements DaoJPA<User> {

    private EntityManager entityManager;

    public User_DaoJpa(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public User_DaoJpa(){
        entityManager = new JpaEntityManagerFactory().getEntityManager();
    }

    @Override
    public Optional<User> getById(int benutzerID) {
        return Optional.ofNullable(entityManager.find(User.class,benutzerID));
    }

    @Override
    public List<User> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);

        Query query = entityManager.createQuery(cr);
        List<User> userList = query.getResultList();

        return userList;
    }

    @Override
    public void save(User user) {
        executeInsideTransaction(entityManager -> entityManager.persist(user));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

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
