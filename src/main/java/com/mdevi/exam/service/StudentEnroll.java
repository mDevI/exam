package com.mdevi.exam.service;

import com.mdevi.exam.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * This class represents service for student enrollment before attend test exam.
 *
 * @author Sergei Belonosov
 * @since 24.04.2018
 */
public class StudentEnroll {
    private final Logger LOGGER = LoggerFactory.getLogger(StudentEnroll.class);

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Method used for input a students first name and last name.
     *
     * @return Student with fulfilled info.
     * @see Student
     */
    public Student enrollStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Welcome to our test system!");
        System.out.println("Enter your first name: ");
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

