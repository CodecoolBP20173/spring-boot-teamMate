package com.codecool.teammate.config;

import com.codecool.teammate.dao.implementation.AnswerDAOImpl;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOInit {
    // Todo - EMF and EM
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");

    QuestionDAOImpl questionDAO = new QuestionDAOImpl(emf.createEntityManager());
    AnswerDAOImpl answerDAO = new AnswerDAOImpl(emf.createEntityManager());

    public DAOInit() {
    }

    public QuestionDAOImpl getQuestionDAO() {
        return questionDAO;
    }

    public AnswerDAOImpl getAnswerDAO() { return answerDAO; }
}
