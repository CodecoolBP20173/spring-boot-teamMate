package com.codecool.teammate.config;

import com.codecool.teammate.dao.implementation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class DAOInit {
    private EntityManager em = EntityManagerUtil.getEm();
    private HashMap<String, Object> daoHashMap = new HashMap<>();

    public DAOInit() {}

    public void start(List<HttpServlet> servlets) {
        createDAOs();
        injectDAOs(servlets);
    }

    private void createDAOs() {
        addDAO(new AnswerDAOImpl(em));
        addDAO(new QuestionDAOImpl(em));
        addDAO(new ReviewDAOImpl(em));
        addDAO(new CustomerDAOImpl(em));
        addDAO(new TopicDAOImpl(em));
        addDAO(new VoteDAOImpl(em));
    }

    private void addDAO(Object object) {
        daoHashMap.put(object.getClass().getTypeName(), object);
    }

    private void injectDAOs(List<HttpServlet> servlets) {
        for (HttpServlet servlet : servlets) {
            for (Field field : servlet.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectDAO.class)) {
                    Object dao = daoHashMap.get(field.getAnnotatedType().getType().getTypeName());
                    if (dao == null) {
                        throw new NoSuchElementException("No such DAO in daoHashMap");
                    }
                    try {
                        boolean accessible = field.isAccessible();

                        field.setAccessible(true);

                        field.set(servlet, dao);

                        field.setAccessible(accessible);

                        System.out.println(servlet.getClass().getName() + " servlets " + field.getName() + " field has been set successfully");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        System.out.println("Setting field failed.");
                    }
                }
            }
        }
    }
}
