package com.mdevi.exam.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentEnrollmentImplTest {

    ClassPathXmlApplicationContext ctx;
    StudentEnrollmentImpl studentEnrollmentImpl;

    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("/app-config-test.xml");
        studentEnrollmentImpl = ctx.getBean("studentEnrollmentBean", StudentEnrollmentImpl.class);

    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }

    @Test
    public void testEnrolledStudent() {

    }
}