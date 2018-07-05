package com.codecool.teammate.controller;

import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import com.codecool.teammate.dao.implementation.TopicDAOImpl;
import org.junit.Test;
import org.mockito.Mockito;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;


public class QuestionServletTest extends Mockito {

    @Test
    public void testServlet() throws IOException {
        QuestionDAOImpl questionDAO = mock(QuestionDAOImpl.class);

        QuestionServlet servlet = new QuestionServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext sc = mock(ServletContext.class);
        TemplateEngine engine = spy(TemplateEngine.class);

        when(sc.getAttribute(any())).thenReturn(engine);
        when(request.getServletContext()).thenReturn(sc);
        when(request.getParameter("id")).thenReturn("177");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        when(questionDAO.findAllQuestion()).thenReturn(Collections.emptyList());
        servlet.setQuestionDAO(questionDAO);


        servlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id"); // only if you want to verify username was called...

    }
}