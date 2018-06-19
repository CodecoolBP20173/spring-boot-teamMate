package com.codecool.teammate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private int id;
    private String title;
    private List<Topic> topics = new ArrayList<>();
    private Answer answer;
    private Date date;
    private Customer customer;

    public Question(String title, List<Topic> topics) {
        this.title = title;
        this.topics = topics;
        this.date = new Date();
    }

    public Question(String title, List<Topic> topics, Customer customer) {
        this.title = title;
        this.topics = topics;
        this.customer = customer;
        this.date = new Date();
    }
}
