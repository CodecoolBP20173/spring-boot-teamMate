package com.codecool.teammate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private List<Review> reviews = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne
    private Question question;
    @OneToMany
    private List<Vote> votes = new ArrayList<>();

    public Answer() {
    }

    public Answer(String description, Customer customer, Question question) {
        this.description = description;
        this.customer = customer;
        this.question = question;
        this.date = new Date();
    }

    public Answer(String description, Question question) {
        this.description = description;
        this.question = question;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", customer=" + customer +
                ", reviews=" + reviews +
                ", date=" + date +
                ", question=" + question +
                ", votes=" + votes +
                '}';
    }
}
