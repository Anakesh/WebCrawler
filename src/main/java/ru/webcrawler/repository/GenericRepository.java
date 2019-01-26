package ru.webcrawler.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Pavel on 26.01.2019.
 */
public class GenericRepository<E> implements IRepository<E> {

    private SessionFactory sessionFactory;
    private Class<E> entityClass;

    public GenericRepository(SessionFactory sessionFactory, Class<E> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public void add(E entity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public List<E> readAll() {
        Session session = getSession();
        EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> entityCriteria = criteriaBuilder.createQuery(entityClass);
        Root<E> entityRoot = entityCriteria.from(entityClass);
        entityCriteria.select(entityRoot);
        List<E> entityList = entityManager.createQuery(entityCriteria).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        session.close();
        return entityList;
    }

    public void update(E entity) {

    }

    public E pop() {
        return null;
    }

    public void delete(E entity) {

    }

    private Session getSession(){
        return sessionFactory.openSession();
    }
}
