package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.common.RandomItemsFromList;
import com.adam.stan.history.quiz.service.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuizPreparationImpl implements QuizPreparation {
    private final QuestionPreparation questionPreparation;

    public QuizPreparationImpl(QuestionPreparation questionPreparation) {
        this.questionPreparation = questionPreparation;
    }

    @Override
    public List<Question> getQuiz(List<QuestionModel> allQuestions, int amountOfQuestions, int answersToChoice) {
        List<Question> questions = new ArrayList<>();
        RandomItemsFromList<QuestionModel> itemsGenerator = new RandomItemsFromList<>(amountOfQuestions, allQuestions);
        for (QuestionModel question : itemsGenerator.getRandomItems()) {
            questions.add(questionPreparation.createQuestion(question, answersToChoice));
        }
        return questions;
    }
}
