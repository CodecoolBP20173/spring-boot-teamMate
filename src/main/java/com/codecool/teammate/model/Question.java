package com.codecool.teammate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @ManyToMany
    private List<Topic> topics = new ArrayList<>();

    @OneToOne
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", topics=" + topics +
                ", answer=" + answer +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
