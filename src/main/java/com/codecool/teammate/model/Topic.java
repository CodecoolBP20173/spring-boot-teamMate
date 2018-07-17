package com.codecool.teammate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany
    private List<Question> questions = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    public Topic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public static Topic create(String name) {
        return new Topic(name);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
