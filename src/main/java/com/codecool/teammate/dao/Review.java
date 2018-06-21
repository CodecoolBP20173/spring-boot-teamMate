package com.codecool.teammate.dao;

public interface Review {

    void add (Review review);
    Review find (int id);
    void remove(int id);

}
