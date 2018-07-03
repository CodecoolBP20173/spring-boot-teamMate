package com.codecool.teammate.dao;

import com.codecool.teammate.model.Customer;

public interface CustomerDAO {

    void add (Customer customer);
    Customer find (int id);
    void remove(int id);
}
