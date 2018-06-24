package com.mdevi.exam.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CsvDataLoaderTest {

    private static Logger logger = LoggerFactory.getLogger(CsvDataLoaderTest.class);
    ClassPathXmlApplicationContext ctx;
    CsvDataLoader loader;
    com.mdevi.exam.model.Test test;

    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("/app-config-test.xml");
        loader = (CsvDataLoader) ctx.getBean("questionLoaderBean");
        // test = ctx.getBean("testQuestionsBean", com.mdevi.exam.model.Test.class);
        assertNotNull(loader);
    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }

    @Test
    public void test1getQuestionListFromCsv() {
        try {
            loader.getQuestionListFromCsv();
            assertEquals(5, test.getQuestionList().size());
        } catch (Exception e) {

        }
    }
}