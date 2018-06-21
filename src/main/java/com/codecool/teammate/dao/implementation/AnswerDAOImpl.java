package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.AnswerDAO;
import com.codecool.teammate.model.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO {
    @Override
    public void add(Answer answer) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        em.persist(answer);
        em.close();
        emf.close();
    }

    @Override
    public Answer find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Answer answerToFind= em.find(Answer.class, id);
        em.close();
        emf.close();
        return answerToFind;
    }

    @Override
    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Answer answerToRemove = find(id);
        if (answerToRemove != null) {
            em.remove(answerToRemove);
        }
        em.close();
        emf.close();

    }
}
