package com.codecool.teammate;

import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoadInitialData {

    public static void main(String[] args) {

        HashMap<String, String> topics = new HashMap<String, String>();
        topics.put("OOP / General", "src/main/webapp/static/data_init/algorithms.txt");
        topics.put("Java language", "src/main/webapp/static/data_init/java_l.txt");
        topics.put("Java enterprise", "src/main/webapp/static/data_init/java_ee.txt");
        topics.put("Databases", "src/main/webapp/static/data_init/databases.txt");
        topics.put("Algorithms, Pseudo code", "src/main/webapp/static/data_init/algorithms.txt");
        topics.put("Networks, HTTP, Web technologies", "src/main/webapp/static/data_init/netw_http_web.txt");
        topics.put("Software methodologies", "src/main/webapp/static/data_init/software_method.txt");
        topics.put("Testing", "src/main/webapp/static/data_init/testing.txt");
        topics.put("Threads, Concurrency", "src/main/webapp/static/data_init/threads_concurrency.txt");

        populateDb(topics);

    }

    public static void populateDb(HashMap<String, String> topics) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teammatePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        loadQuestionsFromTopics(em, topics);

        transaction.commit();
        em.close();
        emf.close();
    }

    private static void loadQuestionsFromTopics(EntityManager em, HashMap<String, String> topics) {
            Set set = topics.entrySet();
            Iterator iterator = set.iterator();
            Topic topic;
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                String topicTitle = (String)mentry.getKey();
                String topicFilePath = (String)mentry.getValue();
                topic = new Topic(topicTitle);
                em.persist(topic);

                try {
                    List<String> questions = readQuestions(topicFilePath);
                    for(String questionString: questions){
                        em.persist(new Question(questionString,topic));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
    }

    private static List <String> readQuestions(String fileName) throws IOException { String fn = fileName;
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
