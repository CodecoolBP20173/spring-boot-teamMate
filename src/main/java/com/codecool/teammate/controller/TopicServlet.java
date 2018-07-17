package com.codecool.teammate.controller;

import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopicServlet extends HttpServlet {

    private QuestionDAOImpl questionDAO;
    private TopicDAOImpl topicDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String idStr = req.getParameter("id");

        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            context.setVariable("topic", topicDAO.find(id));
            context.setVariable("questions", questionDAO.findAllQuestionByTopic(id));
        }

        engine.process("topic.html", context, resp.getWriter());
    }
}
