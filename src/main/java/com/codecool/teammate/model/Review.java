package com.codecool.teammate.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Answer answer;
    @ManyToOne
    private Customer customer;
    private String description;
    private Date date;

    public Review() {
    }

    public Review(String description, Answer answer) {
        this.answer = answer;
        this.customer = customer;
        this.description = description;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Review create(String description, Question question, Answer answer) {
        return new Review(description, answer);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", answer=" + answer +
                ", customer=" + customer +
                ", description='" + description + '\'' +
                '}';
    }
}
