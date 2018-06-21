package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.TopicDAO;
import com.codecool.teammate.model.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TopicDAOImpl implements TopicDAO {
    private EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        emf.close();
        return em;
    }

    @Override
    public void add(Topic topic) {

    }

    @Override
    public Topic find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

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
