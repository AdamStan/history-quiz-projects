package com.adam.stan.history.quiz.service.service.management;

import com.adam.stan.history.quiz.api.v1.model.ErrorDB;
import com.adam.stan.history.quiz.api.v1.model.QuestionDB;

import java.util.List;

public interface QuestionService {
    ErrorDB addQuestion(QuestionDB questionDB);
    void addQuestions(List<QuestionDB> questions);
}
