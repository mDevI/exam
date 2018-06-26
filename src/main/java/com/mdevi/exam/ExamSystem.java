package com.mdevi.exam;

import com.mdevi.exam.service.ExamProcess;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ExamSystem {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/app-config.xml");
        ExamProcess exam = context.getBean("examBean", ExamProcess.class);
        exam.doExam();
        context.close();
    }
}
