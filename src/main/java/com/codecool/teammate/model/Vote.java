package com.codecool.teammate.model;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Answer answer;
    @OneToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    public Vote() {
    }

    public Vote(Answer answer, Customer customer, VoteType voteType) {
        this.answer = answer;
        this.customer = customer;
        this.voteType = voteType;
    }

    public Vote(Answer answer) {
        this.answer = answer;
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

    @Enumerated(EnumType.STRING)
    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "answer=" + answer +
                ", customer=" + customer +
                ", voteType=" + voteType +
                '}';
    }
}
