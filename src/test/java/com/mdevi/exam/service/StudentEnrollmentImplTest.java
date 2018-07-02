package com.mdevi.exam.service;

import com.mdevi.exam.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentEnrollmentImplTest {

    AnnotationConfigApplicationContext ctx;
    StudentEnrollment studentEnrollment;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }

    @Test
    public void testEnrolledStudent() {

    }
}