package com.mdevi.exam.service;

import com.mdevi.exam.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Scanner;

/**
 * This class represents service for student enrollment before attend test exam.
 *
 * @author Sergei Belonosov
 * @since 24.04.2018
 */
@Repository
public class StudentEnrollmentImpl implements StudentEnrollment {
    private final Logger LOGGER = LoggerFactory.getLogger(StudentEnrollmentImpl.class);
    private Student student;
    private Locale locale;

    @Autowired
    private MessageSource messageSource;

    public Student getStudent() {
        return student;
    }

    public StudentEnrollmentImpl() {
        this.student = new Student();
        this.locale = Locale.forLanguageTag("ru_RU");
    }

    public Student enrollStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println(messageSource.getMessage("app.student.enrollment.welcome", new String[]{}, locale));
        System.out.println(messageSource.getMessage("app.student.enrollment.enter.firstName", new String[]{}, locale));
        String firstName = sc.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();
        if (!("".equals(firstName)) && !("".equals(lastName))) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
            LOGGER.info("The new student is enrolled: " + student.toString());
            return student;
        } else {
            LOGGER.error("Wrong data has been entered.");
            return null;
        }
    }


}

