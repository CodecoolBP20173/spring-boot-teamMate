package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.CustomerDAO;
import com.codecool.teammate.model.Customer;

import javax.persistence.EntityManager;

public class CustomerDAOImpl implements CustomerDAO {

    private final EntityManager em;

    public CustomerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add (Customer customer) { em.persist(customer); }

    @Override
    public Customer find(int id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void remove(int id) {
        Customer customerToRemove = find(id);
        if (customerToRemove != null) {
            em.remove(customerToRemove);
        }
    }
}
