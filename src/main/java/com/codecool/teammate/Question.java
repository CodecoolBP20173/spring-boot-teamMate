package com.codecool.teammate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private Topic topic;
    private Answer answer;
    private Date date;
    private Customer customer;

    public Question(String title, Topic topic) {
        this.title = title;
        this.topic = topic;
        this.date = new Date();
    }

    public Question(String title, Topic topic, Customer customer) {
        this.title = title;
        this.topic = topic;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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
                ", topic=" + topic +
                ", answer=" + answer +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
