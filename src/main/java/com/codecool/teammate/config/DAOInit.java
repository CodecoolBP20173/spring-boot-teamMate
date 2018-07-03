package com.codecool.teammate.config;

import com.codecool.teammate.controller.InitializationServlet;
import com.codecool.teammate.dao.implementation.*;
import org.apache.logging.log4j.core.appender.routing.Routes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOInit {
    // Todo - EMF and EM
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
    private static EntityManager em = emf.createEntityManager();

    private HashMap<DAOs, Object> daOsObjectHashMap = new HashMap<>();
    private List<Class<?>> klassesInControllerPackage;

    public DAOInit() {}

    public void start() {
        createDAOs();
        getKlasses();
        injectDAOs();
    }

    private void createDAOs() {
        daOsObjectHashMap.put(DAOs.QUESTION, new QuestionDAOImpl(em));
        daOsObjectHashMap.put(DAOs.CUSTOMER, new CustomerDAOImpl(em));
        daOsObjectHashMap.put(DAOs.ANSWER, new AnswerDAOImpl(em));
        daOsObjectHashMap.put(DAOs.REVIEW, new ReviewDAOImpl(em));
        daOsObjectHashMap.put(DAOs.TOPIC, new TopicDAOImpl(em));
        daOsObjectHashMap.put(DAOs.VOTE, new VoteDAOImpl(em));
    }

    private void injectDAOs() {
        for (Class<?> klass : klassesInControllerPackage) {
            for (Field field : klass.getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectDAO.class)) {
                    DAOs selectedDAO = field.getAnnotation(InjectDAO.class).value();
                    Object dao = daOsObjectHashMap.get(selectedDAO);
                    try {
                        field.set(dao.getClass(), dao);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        System.out.println("Setting field failed.");
                    }
                }
            }
        }
    }

    private void getKlasses() {
        try {
            klassesInControllerPackage = getAllClassesInPackageContaining(InitializationServlet.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Getting classes from controller package failed.");
        }
    }

    private List<Class<?>> getAllClassesInPackageContaining(Class<?> clazz) throws IOException {
        String clazzPackageName = clazz
                .getPackage()
                .getName();

        String clazzPath = clazz
                .getResource(".")
                .getPath();

        Path packagePath = Paths.get(clazzPath)
                .getParent();

        final List<Class<?>> packageClasses = new ArrayList<>();

        Files.walkFileTree(packagePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(
                    Path file, BasicFileAttributes attrs)
                    throws IOException
            {
                String filename =
                        file.getName(file.getNameCount()-1).toString();

                if (filename.endsWith(".class")) {
                    String className = filename.replace(".class", "");

                    try {
                        Class<?> loadedClazz = Class.forName(
                                clazzPackageName + "." + className);

                        packageClasses.add(loadedClazz);
                    }
                    catch(ClassNotFoundException e) {
                        System.err.println(
                                "class not found: " + e.getMessage());
                    }
                }

                return super.visitFile(file, attrs);
            }
        });

        return packageClasses;
    }
}
