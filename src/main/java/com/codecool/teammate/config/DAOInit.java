package com.codecool.teammate.config;

import com.codecool.teammate.dao.implementation.AnswerDAOImpl;
import com.codecool.teammate.dao.implementation.CustomerDAOImpl;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOInit {
    // Todo - EMF and EM
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
    private static EntityManager em = emf.createEntityManager();


    QuestionDAOImpl questionDAO = new QuestionDAOImpl(em);
    AnswerDAOImpl answerDAO = new AnswerDAOImpl(em);
    CustomerDAOImpl customerDAO = new CustomerDAOImpl(em);

    public DAOInit() {
    }

    public QuestionDAOImpl getQuestionDAO() {
        return questionDAO;
    }

    public AnswerDAOImpl getAnswerDAO() { return answerDAO; }

    public CustomerDAOImpl getCustomerDAO() { return customerDAO; }
}
