package com.codecool.teammate.controller;

import com.codecool.teammate.config.DAOs;
import com.codecool.teammate.config.InjectDAO;
import com.codecool.teammate.config.TemplateEngineUtil;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionServlet extends HttpServlet {

    @InjectDAO(DAOs.QUESTION)
    private QuestionDAOImpl questionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String idStr = req.getParameter("id");

        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            context.setVariable("question", questionDAO.find(id));
        }

        engine.process("question.html", context, resp.getWriter());
    }
}
