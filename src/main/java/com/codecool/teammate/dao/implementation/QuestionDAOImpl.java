package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.QuestionDAO;
import com.codecool.teammate.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    private static QuestionDAOImpl instance = null;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");

    private final EntityManager em;

    /* A private Constructor prevents any other class from instantiating.
     */
    private QuestionDAOImpl(EntityManager em) {
        this.em = em;
    }

    public static QuestionDAOImpl getInstance() {
        if (instance == null) {
            instance = new QuestionDAOImpl(emf.createEntityManager());
        }
        return instance;
    }

    @Override
    public void add(Question question) {
        em.persist(question);
    }

    @Override
    public Question find(int id) {
        Question questionToFind = em.find(Question.class, id);

        return questionToFind;
    }

    @Override
    public void remove(int id) {
        Question questionToRemove = find(id);
        if (questionToRemove != null) {
            em.remove(questionToRemove);
        }
    }

    @Override
    public List<Question> findAllQuestion() {
        Query query = em.createQuery("SELECT q FROM Question q");
        List<Question> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Question> findAllQuestionByTopic(int id){
        Query query = em.createQuery("SELECT t.questions FROM Topic t WHERE t.id = ?");
        query.setParameter(0, id);
        List<Question> resultList = query.getResultList();
        return resultList;
    }

}
