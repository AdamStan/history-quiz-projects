package com.adam.stan.history.quiz.service.controller;

import com.adam.stan.history.quiz.api.v1.QuizApi;
import com.adam.stan.history.quiz.api.v1.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class QuizController implements QuizApi {
    @Override
    public ResponseEntity<List<Question>> getQuiz(Integer numberOfQuestions,
                                                  Integer numberOfAnswers,
                                                  List<String> categories) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
