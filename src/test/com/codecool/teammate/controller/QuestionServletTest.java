package com.codecool.teammate.controller;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;  // for non-hamcrest core matchers

import com.codecool.teammate.config.EntityManagerUtil;
import com.codecool.teammate.config.TemplateEngineUtil;
import com.codecool.teammate.dao.implementation.QuestionDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class QuestionServletTest extends Mockito {

    private QuestionServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter response_writer;
    private Map<String, String> parameters;

    @Before
    public void setUp() throws IOException, NoSuchFieldException {
        parameters = new HashMap<String, String>();
        servlet = new QuestionServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        response_writer = new StringWriter();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

       /* Field questionDAOField = servlet.getClass().getDeclaredField("questionDAO");
        try {
            boolean accessible = questionDAOField.isAccessible();
            questionDAOField.setAccessible(true);
            questionDAOField.set(servlet, new QuestionDAOImpl(EntityManagerUtil.getEm()));
            questionDAOField.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Setting field failed.");
        }*/

        when(request.getParameter(anyString())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) {
                return parameters.get((String) invocation.getArguments()[0]);
            }
        });
        when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
    }

    @Test
    public void testPost1() throws Exception {
        parameters.put("id", "1");
        servlet.doGet(request, response);
        assertThat(response_writer.toString(),
                // a non-hamcrest core matcher
                containsString("i'm only looking for this in the bloody long response"));
    }

}
