package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.CustomerDAO;
import com.codecool.teammate.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDAOImpl implements CustomerDAO {

    private static CustomerDAOImpl instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CustomerDAOImpl() {
    }

    public static CustomerDAOImpl getInstance() {
        if (instance == null) {
            instance = new CustomerDAOImpl();
        }
        return instance;
    }

    @Override
    public void add (Customer customer) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        em.persist(customer);

        em.close();
        emf.close();
    }

    @Override
    public Customer find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        Customer customerToFind = em.find(Customer.class, id);

        em.close();
        emf.close();
        return customerToFind;
    }

    @Override
    public void remove(int id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Customer customerToRemove = find(id);
        if (customerToRemove != null) {
            em.remove(customerToRemove);
        }
        em.close();
        emf.close();

    }
}
