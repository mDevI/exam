package com.mdevi.exam.service;

import com.mdevi.exam.model.Question;
import com.mdevi.exam.model.Student;
import com.mdevi.exam.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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
    private MessageSource messageSource;
    private Locale locale;
    private Optional<String> localeString;

    public void setLocaleString(String localeString) {
        this.localeString = Optional.ofNullable(localeString);
    }

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
        if(localeString.isPresent()) {
            locale = Locale.forLanguageTag(localeString.get());
        } else {
            locale = Locale.getDefault();
        }
        theStudent = studentEnrollment.enrollStudent();
        List<Question> questions = testQuestionsLoader.loadTestQuestions();
        test.setQuestionList(questions);

        if (test != null && theStudent != null) {
            printIntro();
            Scanner sc = new Scanner(System.in);
            for (Question question: test.getQuestionList()) {
                System.out.println(messageSource.getMessage("app.test.process.question.number",
                        new Object[]{question.getNumber(),question.getText() }, locale));
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
        System.out.println(messageSource.getMessage("app.test.process.intro.begin", new String[]{}, locale));
        System.out.println(messageSource.getMessage("app.test.process.intro.answer", new String[]{}, locale));
    }

    private void printTestResult() {
        System.out.println("---------------------------------");
        System.out.println(messageSource.getMessage("app.test.process.result.test.end", new String[]{}, locale));
        System.out.println(messageSource.getMessage("app.test.process.result.total",
                new String[]{theStudent.getFirstName(), theStudent.getLastName(), Integer.toString(result)}, locale));
    }
}
