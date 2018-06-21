package com.codecool.teammate.dao;

import com.codecool.teammate.model.Topic;
import java.util.List;

public interface TopicDAO {

    void add(Topic topic);
    Topic find(int id);
    void remove(int id);

    List<Topic> findAllTopic();
}
