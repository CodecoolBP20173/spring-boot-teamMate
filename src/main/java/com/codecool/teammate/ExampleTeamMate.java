package com.codecool.teammate;

import com.codecool.teammate.model.Question;

import com.codecool.teammate.model.Topic;

import org.eclipse.persistence.sessions.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import javax.security.auth.login.Configuration;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;


public class ExampleTeamMate {

    public static void populateDb(EntityManager em) {
        Topic oop = new Topic("OOP / General");
        Topic javal = new Topic ("Java language");
        Topic javae= new Topic ("Java enterprise");

        List<Topic> topics2 = new ArrayList<>();
        topics2.add(oop);
        topics2.add(javal);
        Question question2 = new Question("Question belongs to multiple topics. What is it?",topics2);

        Question question1 = new Question("Test Title1");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(question1);
        em.persist(oop);
        em.persist(javal);
        em.persist(javae);
        em.persist(question2);
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
