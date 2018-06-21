package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReviewDAOImpl implements Review {
    @Override
    public void add(Review review) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        em.persist(review);
        em.close();
        emf.close();
    }

    @Override
    public Review find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Review reviewToFind= em.find(Review.class, id);
        em.close();
        emf.close();
        return reviewToFind;
    }

    @Override
    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Review reviewToRemove = find(id);
        if (reviewToRemove != null) {
            em.remove(reviewToRemove);
        }
        em.close();
        emf.close();
    }
}
