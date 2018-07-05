package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.AnswerDAO;
import com.codecool.teammate.model.Answer;

import javax.persistence.EntityManager;

public class AnswerDAOImpl implements AnswerDAO {

    private final EntityManager em;

    public AnswerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Answer answer) {
        em.getTransaction().begin();
        em.persist(answer);
        em.getTransaction().commit();
    }

    @Override
    public Answer find(int id) {
        return em.find(Answer.class, id);
    }

    @Override
    public void remove(int id) {
        Answer answerToRemove = find(id);
        if (answerToRemove != null) {
            em.remove(answerToRemove);
        }

    }
}
