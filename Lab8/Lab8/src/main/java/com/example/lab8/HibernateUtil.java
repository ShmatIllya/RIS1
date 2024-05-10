package com.example.lab8;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.core.annotation.*;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import javax.imageio.spi.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration().addFile("D:/FCP/SEM6/Rice/Lab8/Lab8/src/main/resources/Developer.hbm.xml").configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
