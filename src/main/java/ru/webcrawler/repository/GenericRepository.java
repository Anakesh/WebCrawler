package ru.webcrawler.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
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
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }catch (HibernateException ex){
            if(transaction!=null) transaction.rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<E> readAll() {
        Session session = getSession();
        EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        List<E> entityList;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> entityCriteria = criteriaBuilder.createQuery(entityClass);
            Root<E> entityRoot = entityCriteria.from(entityClass);
            entityCriteria.select(entityRoot);
            entityList = entityManager.createQuery(entityCriteria).getResultList();
            transaction.commit();
        } catch (RollbackException ex){
            if(transaction!=null) transaction.rollback();
            ex.printStackTrace();
            throw ex;
        }finally {
            entityManager.close();
            session.close();
        }
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
