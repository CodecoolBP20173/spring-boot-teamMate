package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.ReviewDAO;
import com.codecool.teammate.model.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReviewDAOImpl implements ReviewDAO {

    private static ReviewDAOImpl instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ReviewDAOImpl() {
    }

    public static ReviewDAOImpl getInstance() {
        if (instance == null) {
            instance = new ReviewDAOImpl();
        }
        return instance;
    }

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
