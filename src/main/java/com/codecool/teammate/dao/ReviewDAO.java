package com.codecool.teammate.dao;

import com.codecool.teammate.model.Review;

public interface ReviewDAO {

    void add(Review review);
    Review find(int id);
    void remove(int id);

}
