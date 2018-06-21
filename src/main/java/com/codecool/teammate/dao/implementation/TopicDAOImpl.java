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

        return em;
    }

    @Override
    public void add(Topic topic) {
        EntityManager em = getEntityManager();

        em.persist(topic);

        em.close();
    }

    @Override
    public Topic find(int id) {
        EntityManager em = getEntityManager();

        Topic topicToFind = em.find(Topic.class, id);

        em.close();
        return topicToFind;
    }

    @Override
    public void remove(int id) {
        EntityManager em = getEntityManager();

        Topic topicToRemove = find(id);
        if (topicToRemove != null) {
            em.remove(topicToRemove);
        }

        em.close();
    }

    @Override
    public List<Topic> findAllTopic() {
        EntityManager em = getEntityManager();

        Query query = em.createQuery("SELECT t FROM Topic t");
        List<Topic> resultList = query.getResultList();

        em.close();

        return resultList;
    }

}
