package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.TopicDAO;
import com.codecool.teammate.model.Topic;

import javax.persistence.*;
import java.util.List;

public class TopicDAOImpl implements TopicDAO {

    private static TopicDAOImpl instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private TopicDAOImpl() {
    }

    public static TopicDAOImpl getInstance() {
        if (instance == null) {
            instance = new TopicDAOImpl();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        emf.close();
        return em;
    }

    @Override
    public void add(Topic topic) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        em.persist(topic);

        em.close();
        emf.close();
    }

    @Override
    public Topic find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        Topic topicToFind = em.find(Topic.class, id);

        em.close();
        emf.close();
        return topicToFind;
    }

    @Override
    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        Topic topicToRemove = find(id);
        if (topicToRemove != null) {
            em.remove(topicToRemove);
        }

        em.close();
        emf.close();
    }

    @Override
    public List<Topic> findAllTopic() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT t FROM Topic t");
        List<Topic> resultList = query.getResultList();

        em.close();
        emf.close();
        return resultList;
    }

}
