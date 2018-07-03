package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.ReviewDAO;
import com.codecool.teammate.model.Review;

import javax.persistence.EntityManager;

public class ReviewDAOImpl implements ReviewDAO {

    private EntityManager em;

    public ReviewDAOImpl(EntityManager em) { this.em = em; }

    @Override
    public void add(Review review) { em.persist(review); }

    @Override
    public Review find(int id) {
        return em.find(Review.class, id);
    }

    @Override
    public void remove(int id) {
        Review reviewToRemove = find(id);
        if (reviewToRemove != null) {
            em.remove(reviewToRemove);
        }
    }
}
