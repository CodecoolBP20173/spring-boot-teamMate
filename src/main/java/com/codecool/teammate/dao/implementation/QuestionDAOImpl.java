package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.QuestionDAO;
import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    private final EntityManager em;

    public QuestionDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Question question) { em.persist(question); }

    @Override
    public Question find(int id) {
        return em.find(Question.class, id);
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
        Query query = em.createQuery("SELECT q FROM Question q WHERE q.topic.id = ?");
        query.setParameter(0, id);
        List<Question> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Answer findAnswerByQuestion(Question question) {
        Query query = em.createQuery("SELECT answer FROM Question q WHERE q.id = ?");
        query.setParameter(0, question.getId());
        Answer result = (Answer)query.getSingleResult();
        return result;
    }
}
