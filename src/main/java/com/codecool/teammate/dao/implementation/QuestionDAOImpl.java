package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.QuestionDAO;
import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    private EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        emf.close();
        return em;
    }

    @Override
    public void add(Question question) {
        EntityManager em = getEntityManager();
        em.persist(question);
        em.close();
    }

    @Override
    public Question find(int id) {
        EntityManager em = getEntityManager();
        Question questionToFind = em.find(Question.class, id);
        em.close();

        return questionToFind;
    }

    @Override
    public void remove(int id) {
        EntityManager em = getEntityManager();
        Question questionToRemove = find(id);
        if (questionToRemove != null) {
            em.remove(questionToRemove);
        }
        em.close();
    }

    @Override
    public List<Question> findAllQuestion() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT q FROM Question q");
        List<Question> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    @Override
    public List<Question> findAllQuestionByTopic(int id){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT q FROM Question q WHERE q.id = ?");
        query.setParameter(0, id);
        List<Question> resultList = query.getResultList();
        em.close();
        return resultList;
    }

}
