package com.codecool.teammate.controller;

import com.codecool.teammate.config.DAOInit;

import javax.servlet.*;
import javax.servlet.http.*;

public class InitializationServlet extends HttpServlet
{
    public void init() throws ServletException
    {
        IndexServlet indexServlet = new IndexServlet();
        QuestionServlet questionServlet = new QuestionServlet();
        TopicServlet topicServlet = new TopicServlet();

        getServletContext().setAttribute("indexServlet", indexServlet);
        getServletContext().setAttribute("questionServlet", questionServlet);
        getServletContext().setAttribute("topicServlet", topicServlet);

        DAOInit daoInit = new DAOInit();
        daoInit.start();
    }
}
