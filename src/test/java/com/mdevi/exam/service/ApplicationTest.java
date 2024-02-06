package com.mdevi.exam.service;

import com.mdevi.exam.config.AppConfig;
import com.mdevi.exam.model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationTest {

    AnnotationConfigApplicationContext ctx;
    StudentEnrollment enrollment;
    TestQuestionsLoader loader;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);


    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }

    @Test
    public void testEnrolledStudentCreation() {
        enrollment = ctx.getBean("studentEnrollment", StudentEnrollment.class);
        assertNotNull(enrollment);
    }

    @Test
    public void testLoadQuestionsFromCsvInLocales() {
        loader = ctx.getBean("testQuestionsLoader", TestQuestionsLoader.class);
        assertNotNull(loader);
        ((TestQuestionsLoaderImpl) loader).setFileName("questions.csv");
        ((TestQuestionsLoaderImpl) loader).setType(Question.class);
        ((TestQuestionsLoaderImpl) loader).setLocaleString("en-US");
        List<Question> list = loader.loadTestQuestions();
        assertNotNull(list);
        assertEquals(5, list.size());
        ((TestQuestionsLoaderImpl) loader).setLocaleString("ru-RU");
        list = null;
        list = loader.loadTestQuestions();
        assertNotNull(list);
        assertEquals(5, list.size());

    }

}
