package com.codecool.teammate.dao;

import com.codecool.teammate.model.Topic;
import com.codecool.teammate.model.Vote;

public interface VoteDAO {

    void add(Vote vote);
    Vote find(int id);
    void remove(int id);

}
