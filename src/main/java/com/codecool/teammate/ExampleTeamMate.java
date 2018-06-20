package com.codecool.teammate;

import com.codecool.teammate.model.Question;
import org.eclipse.persistence.internal.oxm.schema.model.List;
import org.eclipse.persistence.sessions.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import javax.security.auth.login.Configuration;
import javax.transaction.Transaction;


public class ExampleTeamMate {

    public static void populateDb(EntityManager em) {
        Question question1 = new Question("Test Title1");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(question1);
        transaction.commit();
        System.out.println("Question 1 saved.");
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);

        Question foundQuestion1 = em.find(Question.class, 1);
        System.out.println("--Found question #1");
        System.out.println("----title----" + foundQuestion1.getTitle());

        em.close();
        emf.close();


    }
}
