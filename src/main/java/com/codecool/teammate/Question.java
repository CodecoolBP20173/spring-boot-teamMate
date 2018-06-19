package com.codecool.teammate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private int id;
    private String title;
    private List<Topic> topics = new ArrayList<>();
    private Answer answer;
    private Date date;
    private Customer customer;
}
