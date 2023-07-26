package com.adam.stan.history.quiz.service.controller;

import com.adam.stan.history.quiz.api.v1.QuizApi;
import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController implements QuizApi {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @Override
    public ResponseEntity<List<Question>> getQuiz(Integer numberOfQuestions,
                                                  Integer numberOfAnswers,
                                                  List<String> categories) {
        // TODO: what if:
        // category doesn't exists
        // not enough answers
        // not enough questions??
        List<Question> questions = quizService.getQuiz(numberOfQuestions, numberOfAnswers, categories);
        return ResponseEntity.ok(questions);
    }
}
