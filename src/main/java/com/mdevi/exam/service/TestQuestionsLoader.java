package com.mdevi.exam.service;

import com.mdevi.exam.model.Question;

import java.util.List;

public interface TestQuestionsLoader {
    List<Question> loadTestQuestions();
}
