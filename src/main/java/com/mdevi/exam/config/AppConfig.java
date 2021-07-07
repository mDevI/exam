package com.mdevi.exam.config;

import com.mdevi.exam.model.Question;
import com.mdevi.exam.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Optional;

@Configuration
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@ComponentScan(basePackages = "com.mdevi.exam")
public class AppConfig {

    @Value("${app.questions.csv.file}")
    private String csvFileName;
    @Value("${app.locale.string}")
    private String localeString;


    @Bean
    public StudentEnrollment studentEnrollment() {
        StudentEnrollmentImpl studentEnrollment =
                new StudentEnrollmentImpl(Optional.ofNullable(localeString), messageSource());
        return studentEnrollment;
    }

    @Bean
    public TestQuestionsLoader testQuestionsLoader() {
        TestQuestionsLoaderImpl loader =
                new TestQuestionsLoaderImpl(Optional.ofNullable(localeString));
        loader.setFileName(csvFileName);
        loader.setType(Question.class);
        return loader;
    }

    @Bean

    public TestProcessor testProcessor() {
        TestProcessorImpl testProcessor =
                new TestProcessorImpl(studentEnrollment(),testQuestionsLoader(),messageSource(),Optional.ofNullable(localeString));
        return testProcessor;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
