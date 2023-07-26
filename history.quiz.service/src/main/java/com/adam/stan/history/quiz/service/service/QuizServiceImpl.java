package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.repository.QuestionModelRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizServiceImpl implements QuizService {

    private final QuizPreparation quizPreparation;
    private final QuestionModelRepository questionRepository;

    public QuizServiceImpl(QuizPreparation quizPreparation, QuestionModelRepository questionRepository) {
        this.quizPreparation = quizPreparation;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuiz(Integer numberOfQuestions, Integer numberOfAnswers, List<String> categories) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
