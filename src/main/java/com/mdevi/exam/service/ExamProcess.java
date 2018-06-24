package com.mdevi.exam.service;

import com.mdevi.exam.model.Question;
import com.mdevi.exam.model.Student;
import com.mdevi.exam.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Class for processing examination.
 *
 * @author Sergei Belonosov
 * @since 24.06.2018
 */
public class ExamProcess {
    private static Logger logger = LoggerFactory.getLogger(ExamProcess.class);
    private Test test;
    private Student student;
    private int result = 0;

    public void setTest(Test test) {
        this.test = test;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void doExam() {

        if (test != null && student != null) {
            printIntro();
            Scanner sc = new Scanner(System.in);
            for (Question question : test.getQuestionList()) {
                System.out.println("Question #" + question.getNumber() + ": " + question.getText());
                String testAnswer = sc.nextLine();
                if (testAnswer.equals(question.getAnswer())) {
                    result++;
                }
            }
            printTestResult();
        } else {
            logger.error("There aren't sufficient conditions to process test.");
        }
    }

    private void printIntro() {
        System.out.println("---------------------------------");
        System.out.println("\t\t Test begins");
        System.out.println("Please, answer the questions. \n");
    }

    private void printTestResult() {
        System.out.println("---------------------------------");
        System.out.println("The test accomplished.");
        System.out.printf("Student: %s %s has test result: %d \n\n", student.getFirstName(), student.getLastName(), result);
    }
}
