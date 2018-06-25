package com.mdevi.exam.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StudentEnrollTest {

    ClassPathXmlApplicationContext ctx;
    StudentEnroll studentEnroll;

    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("/app-config-test.xml");
        studentEnroll = ctx.getBean("studentEnrollmentBean", StudentEnroll.class);
        assertNotNull(studentEnroll.getStudent());
    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }

    @Test
    public void testEnrolledStudent() {
        assertEquals("Doe", studentEnroll.getStudent().getLastName());
    }
}