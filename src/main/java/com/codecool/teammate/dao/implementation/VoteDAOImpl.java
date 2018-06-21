package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.VoteDAO;
import com.codecool.teammate.model.Vote;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VoteDAOImpl implements VoteDAO {

    private static VoteDAOImpl instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private VoteDAOImpl() {
    }

    public static VoteDAOImpl getInstance() {
        if (instance == null) {
            instance = new VoteDAOImpl();
        }
        return instance;
    }

    @Override
    public void add(Vote vote) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        em.persist(vote);

        em.close();
        emf.close();
    }

    @Override
    public Vote find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        Vote voteToFind = em.find(Vote.class, id);

        em.close();
        emf.close();
        return voteToFind;
    }

    @Override
    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        Vote voteToRemove = find(id);
        if (voteToRemove != null) {
            em.remove(voteToRemove);
        }

        em.close();
        emf.close();

    }
}
