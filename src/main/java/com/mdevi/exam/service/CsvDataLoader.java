package com.mdevi.exam.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mdevi.exam.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Class for load questions text and answers from CSV file.
 *
 * @author Sergei Belonosov
 * @since 24.06.2018
 */
public class CsvDataLoader {
    private static Logger logger = LoggerFactory.getLogger(CsvDataLoader.class);
    private Test test;
    private String fileName;
    private Class<?> type;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    /**
     * Method parses CSV file to make list of test questions and its answers.
     */
    public void getQuestionListFromCsv() {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            List list = new CsvMapper().readerFor(type)
                    .with(bootstrapSchema.withColumnSeparator(bootstrapSchema.DEFAULT_COLUMN_SEPARATOR))
                    .readValues(CsvDataLoader.class.getClassLoader().getResourceAsStream(fileName)).readAll();
            test.setQuestionList(list);
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            test.setQuestionList(Collections.emptyList());
        }
    }
}
