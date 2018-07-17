package com.codecool.teammate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private int id;
    @Column (length = 1024)
    private String title;
    @ManyToOne
    private Topic topic;
    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Answer answer;

    private Date date;

    @ManyToOne
    private Customer customer;

    public Question() {
    }

    public Question(String title) {
        this.title = title;
        this.date = new Date();
    }

    public Question(String title, Topic topic) {
        this.title = title;
        this.date = new Date();
        this.topic = topic;
    }

    public Question(String title, Topic topic, Customer customer) {
        this.title = title;
        this.topic = topic;
        this.customer = customer;
        this.date = new Date();
    }

    public Question(String title, Topic topic, List<Tag> tags, Customer customer) {
        this.title = title;
        this.topic = topic;
        this.tags = tags;
        this.customer = customer;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topic getTopic() { return topic; }

    public void setTopic(Topic topic) { this.topic = topic; }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static Question create(String title, Topic topic, List<Tag> tags, Customer customer) {
        return new Question(title, topic, tags, customer);
    }

    public static Question create(String title, Topic topic, Customer customer) {
        return new Question(title, topic, customer);
    }

    public static Question create(String title, Topic topic) {
        return new Question(title, topic);
    }

    public static Question create(String title) {
        return new Question(title);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                '}';
    }
}
