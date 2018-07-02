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

    private static QuestionDAOImpl instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private QuestionDAOImpl() {
    }

    public static QuestionDAOImpl getInstance() {
        if (instance == null) {
            instance = new QuestionDAOImpl();
        }
        return instance;
    }

    @Override
    public void add(Question question) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        em.persist(question);
        em.close();
        emf.close();
    }

    @Override
    public Question find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Question questionToFind = em.find(Question.class, id);
        em.close();
        emf.close();

        return questionToFind;
    }

    @Override
    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Question questionToRemove = find(id);
        if (questionToRemove != null) {
            em.remove(questionToRemove);
        }
        em.close();
        emf.close();
    }

    @Override
    public List<Question> findAllQuestion() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT q FROM Question q");
        List<Question> resultList = query.getResultList();
        em.close();
        emf.close();
        return resultList;
    }

    @Override
    public List<Question> findAllQuestionByTopic(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT q FROM Question q WHERE q.topic.id = ?");
        query.setParameter(0, id);
        List<Question> resultList = query.getResultList();
        em.close();
        emf.close();
        return resultList;
    }

}
