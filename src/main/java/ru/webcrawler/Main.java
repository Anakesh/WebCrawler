package ru.webcrawler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.webcrawler.entity.Page;
import ru.webcrawler.repository.GenericRepository;

import javax.persistence.EntityManager;
import java.util.Arrays;

/**
 * Created by Pavel on 06.01.2019.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try{
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception ex){
            StandardServiceRegistryBuilder.destroy(registry);
            throw ex;
        }
        GenericRepository<Page> genericRepository = new GenericRepository<Page>(sessionFactory,Page.class);
        Page page1 = new Page();
        page1.setText("hello");
        Page page2 = new Page();
        page2.setText("world");
        page2.setId(2);
        genericRepository.add(page1);
        genericRepository.add(page2);
        System.out.println(Arrays.toString(genericRepository.readAll().toArray()));
    }
}
