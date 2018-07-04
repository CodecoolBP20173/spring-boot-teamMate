package com.codecool.teammate.dao.implementation;

import com.codecool.teammate.dao.TopicDAO;
import com.codecool.teammate.model.Topic;

import javax.persistence.*;
import java.util.List;

public class TopicDAOImpl implements TopicDAO {

    private EntityManager em;

    public TopicDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Topic topic) { em.persist(topic); }

    @Override
    public Topic find(int id) {
        return em.find(Topic.class, id);
    }

    @Override
    public void remove(int id) {
        Topic topicToRemove = find(id);
        if (topicToRemove != null) {
            em.remove(topicToRemove);
        }
    }

    @Override
    public List<Topic> findAllTopic() {
        Query query = em.createQuery("SELECT t FROM Topic t");
        List<Topic> resultList = query.getResultList();
        return resultList;
    }

}
