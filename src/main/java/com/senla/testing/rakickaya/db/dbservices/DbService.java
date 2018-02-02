package com.senla.testing.rakickaya.db.dbservices;

import com.senla.testing.rakickaya.db.dbentities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbService {
    private static final long ID_USER = 4L;
    private SessionFactory sessionFactory;

    public DbService() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

    public Employee getEmployee() {
        Employee empl;
        try (Session session = sessionFactory.openSession()) {
            empl = session.find(Employee.class, ID_USER);
        }
        return empl;
    }
}
