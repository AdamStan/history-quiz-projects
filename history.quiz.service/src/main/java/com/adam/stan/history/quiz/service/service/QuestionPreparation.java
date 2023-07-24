package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.model.QuestionModel;

public interface QuestionPreparation {
    Question createQuestion(QuestionModel question, int amountOfChoices);
}
