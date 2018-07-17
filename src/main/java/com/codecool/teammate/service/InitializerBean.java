package com.codecool.teammate.service;

import com.codecool.teammate.model.Topic;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(TopicRepository topicRepository) {
        topicRepository.save(Topic.create("OOP / General"));
        topicRepository.save(Topic.create("Java language"));
        topicRepository.save(Topic.create("Java enterprise"));
        topicRepository.save(Topic.create("Databases"));
        topicRepository.save(Topic.create("Algorithms, Pseudo code"));
        topicRepository.save(Topic.create("Networks, HTTP, Web technologies"));
        topicRepository.save(Topic.create("Software methodologies"));
        topicRepository.save(Topic.create("Testing"));
        topicRepository.save(Topic.create("Threads, Concurrency"));
    }
}
