package com.mdevi.exam.service;

import com.mdevi.exam.model.Question;
import com.mdevi.exam.model.Student;
import com.mdevi.exam.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TestProcessorImpl implements TestProcessor {

    private final Logger logger = LoggerFactory.getLogger(TestProcessorImpl.class);
    private final StudentEnrollment studentEnrollment;
    private Student theStudent;
    private Test test;
    private int result;
    private TestQuestionsLoader testQuestionsLoader;

    @Autowired
    public TestProcessorImpl(StudentEnrollment studentEnrollment) {
        this.studentEnrollment = studentEnrollment;
        this.test = new Test();
    }

    @Autowired
    public void setTestQuestionsLoader(TestQuestionsLoader testQuestionsLoader) {
        this.testQuestionsLoader = testQuestionsLoader;
    }

    @Override
    public void doTest() {
        result = 0;
        theStudent = studentEnrollment.enrollStudent();
        System.out.println(theStudent.toString());
        List<Question> questions = testQuestionsLoader.loadTestQuestions();
        test.setQuestionList(questions);

        if (test != null && theStudent != null) {
            printIntro();
            Scanner sc = new Scanner(System.in);
            for (Question question: test.getQuestionList()) {
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
        System.out.printf("Student: %s %s has test result: %d \n\n", theStudent.getFirstName(), theStudent.getLastName(), result);
    }
}
