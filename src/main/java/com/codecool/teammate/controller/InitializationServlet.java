package com.codecool.teammate.controller;

import com.codecool.teammate.config.DAOInit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

public class InitializationServlet extends HttpServlet
{
    public void init() throws ServletException
    {
        List<HttpServlet> servlets = new ArrayList<>();

        IndexServlet indexServlet = new IndexServlet();
        QuestionServlet questionServlet = new QuestionServlet();
        TopicServlet topicServlet = new TopicServlet();

        servlets.add(indexServlet);
        servlets.add(questionServlet);
        servlets.add(topicServlet);

        getServletContext().setAttribute("indexServlet", indexServlet);
        getServletContext().setAttribute("questionServlet", questionServlet);
        getServletContext().setAttribute("topicServlet", topicServlet);

        DAOInit daoInit = new DAOInit();
        daoInit.start(servlets);
    }
}
