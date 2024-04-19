package com.main.crud;

import hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Employee emp = new Employee("Petr", "Vasilivich", "IT", 500);

        session.beginTransaction();
        session.save(emp);
        session.getTransaction().commit();

        int id = emp.getId();

        session = factory.openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();

        session = factory.openSession();
        session.beginTransaction();

        List<Employee> emps = session.createQuery("from Employee").getResultList();

        for (Employee e : emps) {
            System.out.println(e);
        }

        session.getTransaction().commit();

        session = factory.openSession();
        session.beginTransaction();

        session.createQuery("update Employee set salary=1000 " + "where firstName = 'Petr'").executeUpdate();

        session.getTransaction().commit();

        session.getTransaction().commit();

        session = factory.openSession();
        session.beginTransaction();

        session.createQuery("delete Employee " + "where firstName = 'Petr'").executeUpdate();

        session.getTransaction().commit();

        factory.close();

    }
}
