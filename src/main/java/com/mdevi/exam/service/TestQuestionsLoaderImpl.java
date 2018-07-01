package com.mdevi.exam.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mdevi.exam.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class TestQuestionsLoaderImpl implements TestQuestionsLoader {

    private final Logger LOGGER = LoggerFactory.getLogger(TestQuestionsLoaderImpl.class);

    private String fileName;
    private Class<?> type;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public TestQuestionsLoaderImpl() {
    }

    @Override
    public List<Question> loadTestQuestions() {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            List list = mapper.readerFor(type)
                    .with(bootstrapSchema.withColumnSeparator(bootstrapSchema.DEFAULT_COLUMN_SEPARATOR))
                    .readValues(TestQuestionsLoaderImpl.class.getClassLoader().getResourceAsStream(fileName)).readAll();
            return list;
        } catch (Exception e) {
            LOGGER.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
