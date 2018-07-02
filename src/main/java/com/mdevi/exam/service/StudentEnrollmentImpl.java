package com.mdevi.exam.service;

import com.mdevi.exam.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;
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
    @Autowired
    private MessageSource messageSource;
    private Locale locale;
    private Optional<String> localeString;

    public void setLocaleString(String localeString) {
        this.localeString = Optional.ofNullable(localeString);
    }

    public Student getStudent() {
        return student;
    }

    public StudentEnrollmentImpl() {
        this.student = new Student();
    }

    public Student enrollStudent() {
        if(localeString.isPresent()) {
            locale = Locale.forLanguageTag(localeString.get());
        } else {
            locale = Locale.getDefault();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println(messageSource.getMessage("app.student.enrollment.welcome", new String[]{}, locale));
        System.out.println(messageSource.getMessage("app.student.enrollment.enter.firstName", new String[]{}, locale));
        String firstName = sc.nextLine();
        System.out.println(messageSource.getMessage("app.student.enrollment.enter.lastName", new String[]{}, locale));
        String lastName = sc.nextLine();
        if (!("".equals(firstName)) && !("".equals(lastName))) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
            // LOGGER.info("The new student is enrolled: " + student.toString());
            return student;
        } else {
            LOGGER.error("Wrong data has been entered.");
            return null;
        }
    }


}

