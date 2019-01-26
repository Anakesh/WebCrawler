package ru.webcrawler.repository;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by Pavel on 26.01.2019.
 */
public interface IRepository<E> {
    void add(E entity);
    List<E> readAll();

    void update(E entity);
    E pop();
    void delete(E entity);
}
