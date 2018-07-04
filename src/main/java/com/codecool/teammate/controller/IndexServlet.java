package com.codecool.teammate.controller;

import com.codecool.teammate.config.InjectDAO;
import com.codecool.teammate.config.TemplateEngineUtil;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import com.codecool.teammate.dao.implementation.TopicDAOImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @InjectDAO
    private TopicDAOImpl topicDAO;

    @InjectDAO
    private QuestionDAOImpl questionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

         TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
         WebContext context = new WebContext(req, resp, req.getServletContext());
         context.setVariable("searched_questions", questionDAO.findAllQuestionBySubstring("What"));
         context.setVariable("topics", topicDAO.findAllTopic());
         engine.process("index.html", context, resp.getWriter());
    }
}
