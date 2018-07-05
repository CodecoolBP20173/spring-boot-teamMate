package com.codecool.teammate.controller;

import com.codecool.teammate.config.TemplateEngineUtil;
import com.codecool.teammate.config.ThymeleafConfig;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import com.codecool.teammate.dao.implementation.TopicDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;

import static org.junit.Assert.*;

public class IndexServletTest extends Mockito {

    @Test
    public void testServlet() throws IOException{
        TopicDAOImpl topicDAO = mock(TopicDAOImpl.class);
        QuestionDAOImpl questionDAO = mock(QuestionDAOImpl.class);

        IndexServlet servlet = new IndexServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext sc = mock(ServletContext.class);
        TemplateEngine engine = spy(TemplateEngine.class);
        when(sc.getAttribute(any())).thenReturn(engine);
        when(request.getServletContext()).thenReturn(sc);
        when(request.getParameter("id")).thenReturn("176");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        when(topicDAO.findAllTopic()).thenReturn(Collections.emptyList());
        servlet.setTopicDAO(topicDAO);


        servlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("searched_string"); // only if you want to verify username was called...

    }


}