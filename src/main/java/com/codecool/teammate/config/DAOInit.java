package com.codecool.teammate.config;

import com.codecool.teammate.dao.implementation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class DAOInit {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
    private static EntityManager em = emf.createEntityManager();

    private HashMap<DAOs, Object> daOsObjectHashMap = new HashMap<>();

    public DAOInit() {}

    public void start(List<HttpServlet> servlets) {
        createDAOs();
        injectDAOs(servlets);
    }

    private void createDAOs() {
        daOsObjectHashMap.put(DAOs.ANSWER, new AnswerDAOImpl(em));
        daOsObjectHashMap.put(DAOs.CUSTOMER, new CustomerDAOImpl(em));
        daOsObjectHashMap.put(DAOs.QUESTION, new QuestionDAOImpl(em));
        daOsObjectHashMap.put(DAOs.REVIEW, new ReviewDAOImpl(em));
        daOsObjectHashMap.put(DAOs.TOPIC, new TopicDAOImpl(em));
        daOsObjectHashMap.put(DAOs.VOTE, new VoteDAOImpl(em));
    }

    private void injectDAOs(List<HttpServlet> servlets) {
        for (HttpServlet servlet : servlets) {
            for (Field field : servlet.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectDAO.class)) {
                    DAOs selectedDAO = field.getAnnotation(InjectDAO.class).value();
                    Object dao = daOsObjectHashMap.get(selectedDAO);
                    try {
                        boolean accessible = field.isAccessible();

                        field.setAccessible(true);

                        field.set(servlet, dao);

                        field.setAccessible(accessible);

//                        System.out.println(servlet.getClass().getName() + " servlets " + field.getName() + " field has been set successfully");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        System.out.println("Setting field failed.");
                    }
                }
            }
        }
    }
}
