package com.codecool.teammate.config;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
    private static javax.persistence.EntityManager em = emf.createEntityManager();


    public static EntityManager getEm() {
        return em;
    }
}
