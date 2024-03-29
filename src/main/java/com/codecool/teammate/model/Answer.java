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
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private List<Review> reviews = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne(mappedBy = "answer", cascade = CascadeType.ALL)
    private Question question;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();

    public Answer() {
    }

    public Answer(String description, Customer customer, Question question) {
        this.description = description;
        this.customer = customer;
        this.question = question;
        this.date = new Date();
        question.setAnswer(this);
    }

    public Answer(String description, Question question) {
        this.description = description;
        this.question = question;
        this.date = new Date();
        question.setAnswer(this);
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

    public static Answer create(String description, Customer customer, Question question) {
        return new Answer(description, customer, question);
    }

    public static Answer create(String description, Question question) {
        return new Answer(description, question);
    }


    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                '}';
    }
}
