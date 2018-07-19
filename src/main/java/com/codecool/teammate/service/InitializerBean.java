package com.codecool.teammate.service;

import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

    @Component
    public class InitializerBean {

        public InitializerBean(TopicRepository topicRepository, QuestionRepository questionRepository) {

            HashMap<String, String> topics = new HashMap<String, String>();
            topics.put("OOP / General", "src/main/resources/static/data_init/algorithms.txt");
            topics.put("Java language", "src/main/resources/static/data_init/java_l.txt");
            topics.put("Java enterprise", "src/main/resources/static/data_init/java_ee.txt");
            topics.put("Databases", "src/main/resources/static/data_init/databases.txt");
            topics.put("Algorithms, Pseudo code", "src/main/resources/static/data_init/algorithms.txt");
            topics.put("Networks, HTTP, Web technologies", "src/main/resources/static/data_init/netw_http_web.txt");
            topics.put("Software methodologies", "src/main/resources/static/data_init/software_method.txt");
            topics.put("Testing", "src/main/resources/static/data_init/testing.txt");
            topics.put("Threads, Concurrency", "src/main/resources/static/data_init/threads_concurrency.txt");

            addTopicsAndQuestions(topicRepository, questionRepository, topics);
        }

        private void addTopicsAndQuestions(TopicRepository topicRepository, QuestionRepository questionRepository, HashMap<String, String> topics) {
            Set set = topics.entrySet();
            Iterator iterator = set.iterator();
            Topic topic;
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                String topicTitle = (String) mentry.getKey();
                String topicFilePath = (String) mentry.getValue();
                topic = Topic.create(topicTitle);
                topicRepository.save(topic);
                addQuestions(questionRepository, topicFilePath, topic);
            }
        }

        private void addQuestions(QuestionRepository questionRepository, String topicFilePath, Topic topic) {
            try {
                List<String> questions = readQuestions(topicFilePath);
                for(String questionString: questions){
                    questionRepository.save(Question.create(questionString, topic));
                }
            } catch (IOException e) {
                e.printStackTrace();
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
