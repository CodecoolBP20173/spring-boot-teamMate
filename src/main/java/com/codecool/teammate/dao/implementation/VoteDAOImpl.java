package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.VoteDAO;
import com.codecool.teammate.model.Vote;

import javax.persistence.EntityManager;

public class VoteDAOImpl implements VoteDAO {

    private EntityManager em;

    public VoteDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Vote vote) { em.persist(vote); }

    @Override
    public Vote find(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public void remove(int id) {
        Vote voteToRemove = find(id);
        if (voteToRemove != null) {
            em.remove(voteToRemove);
        }
    }
}
