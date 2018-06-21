package com.codecool.teammate.dao;

import com.codecool.teammate.model.Answer;

import java.util.List;

public interface AnswerDAO {
    void add (Answer answer);
    Answer find (int id);
    void remove(int id);
}
