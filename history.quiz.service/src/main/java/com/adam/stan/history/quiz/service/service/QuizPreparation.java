package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.model.QuestionModel;

import java.util.List;

public interface QuizPreparation {
    List<Question> getQuiz(List<QuestionModel> allQuestions, int amountOfQuestions, int answersToChoice);
}
