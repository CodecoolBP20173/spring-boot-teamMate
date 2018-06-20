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
    private String title;
    @ManyToMany
    private List<Topic> topics = new ArrayList<>();

    @OneToOne
    private Answer answer;

    private Date date;

    @Transient
    private long age;

    @ManyToOne
    private Customer customer;

    public Question() {
    }

    public Question(String title, Date date) {
        this.title = title;
        this.date = date;
        this.age = (Calendar.getInstance().getTimeInMillis() - date.getTime())
                / (60L * 60L * 1000L * 24L);
    }

    public Question(String title, List<Topic> topics, Date date) {
        this.title = title;
        this.topics = topics;
        this.date = date;
        this.age = (Calendar.getInstance().getTimeInMillis() - date.getTime())
                / (60L * 60L * 1000L * 24L);
    }

    public Question(String title, List<Topic> topics, Customer customer, Date date) {
        this.title = title;
        this.topics = topics;
        this.customer = customer;
        this.date = date;
        this.age = (Calendar.getInstance().getTimeInMillis() - date.getTime())
                / (60L * 60L * 1000L * 24L);
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
