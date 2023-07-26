package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;

import java.util.List;

public interface QuizService {
    List<Question> getQuiz(Integer numberOfQuestions, Integer numberOfAnswers, List<String> categories);
}
