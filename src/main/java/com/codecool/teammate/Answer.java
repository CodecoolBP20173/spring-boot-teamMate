package com.codecool.teammate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer {
    private int id;
    private String description;
    private Customer customer;
    private List<Review> reviews = new ArrayList<>();
    private Date date;
    private Question question;
    private List<Vote> votes = new ArrayList<>();
}
