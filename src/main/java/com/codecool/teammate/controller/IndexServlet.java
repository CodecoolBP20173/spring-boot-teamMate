package com.codecool.teammate.controller;

import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    private TopicDAOImpl topicDAO;

    private QuestionDAOImpl questionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // Get Parameters From The Request
        String searchedString = req.getParameter("searched_string");

        if (searchedString != null) {
            context.setVariable("searched_string", searchedString);
            context.setVariable("searched_questions", questionDAO.findAllQuestionBySubstring(searchedString));
        }

        context.setVariable("topics", topicDAO.findAllTopic());
        engine.process("index.html", context, resp.getWriter());
    }

    public void setTopicDAO(TopicDAOImpl topicDAO) {
        this.topicDAO = topicDAO;
    }
}
