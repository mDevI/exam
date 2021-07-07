package com.mdevi.exam;

import com.mdevi.exam.config.AppConfig;
import com.mdevi.exam.service.TestProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExamSystem {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        TestProcessor testProcessor = ctx.getBean( TestProcessor.class);
        testProcessor.doTest();
        ctx.close();
    }
}
