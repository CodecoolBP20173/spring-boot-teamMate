package com.codecool.teammate.controller;

import com.codecool.teammate.config.TemplateEngineUtil;
import com.codecool.teammate.dao.implementation.TopicDAOImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         TopicDAOImpl topicDAO = TopicDAOImpl.getInstance();

         TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
         WebContext context = new WebContext(req, resp, req.getServletContext());

         context.setVariable("topics", topicDAO.findAllTopic());
         engine.process("index.html", context, resp.getWriter());
    }
}
