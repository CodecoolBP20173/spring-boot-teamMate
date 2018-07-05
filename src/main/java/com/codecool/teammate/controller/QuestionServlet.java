package com.codecool.teammate.controller;

import com.codecool.teammate.config.InjectDAO;
import com.codecool.teammate.config.TemplateEngineUtil;
import com.codecool.teammate.dao.implementation.AnswerDAOImpl;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionServlet extends HttpServlet {

    @InjectDAO
    private QuestionDAOImpl questionDAO;

    @InjectDAO
    private AnswerDAOImpl answerDAO;

/*    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String idStr = req.getParameter("id");

        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Question question = questionDAO.find(id);
            context.setVariable("question", question);
            Answer answer = question.getAnswer();
            addAnswerToContext(context, question, answer);


            context.setVariable("answer", "<b>KUTY</b>a");
        }

        engine.process("question.html", context, resp.getWriter());
    }

    private void addAnswerToContext(WebContext context, Question question, Answer answer) {
        if (answer != null) {
            context.setVariable("answer", question.getAnswer());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
    }
}*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String idStr = req.getParameter("id");

        addToContext(context, idStr);

        engine.process("question.html", context, resp.getWriter());
    }

    private void addToContext(WebContext context, String idStr) {
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Question question = questionDAO.find(id);
            context.setVariable("question", question);
            addAnswerToContext(context, question);
        }
    }

    private void addAnswerToContext(WebContext context, Question question) {
        if (question.getAnswer() != null) {
            String answerFromDB = question.getAnswer().getDescription();
            context.setVariable("answer_description", answerFromDB);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String answer_input = req.getParameter("answer_input");
        System.out.println("this is the answer sent by html" + answer_input);
        String idStr = req.getParameter("question_id");
        System.out.println(idStr);

        addNewAnswer(answer_input, idStr);

        resp.sendRedirect("/questions?id=" + idStr);
    }

    private void addNewAnswer(String answer_input, String idStr) {
        if (idStr != null) {
            int question_id = Integer.parseInt(idStr);
            Question question = questionDAO.find(question_id);
            Answer answer = new Answer(answer_input, question);
            answerDAO.add(answer);
        }
    }
}
