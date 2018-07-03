package com.codecool.teammate.config;

import com.codecool.teammate.dao.implementation.QuestionDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOInit {
    // Todo - EMF and EM
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");

    QuestionDAOImpl questionDAO = new QuestionDAOImpl(emf.createEntityManager());

    public DAOInit() {
    }

    public QuestionDAOImpl getQuestionDAO() {
        return questionDAO;
    }
}
