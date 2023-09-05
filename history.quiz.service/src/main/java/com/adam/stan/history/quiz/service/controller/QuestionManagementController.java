package com.adam.stan.history.quiz.service.controller;

import com.adam.stan.history.quiz.api.v1.QuestionManagementApi;
import com.adam.stan.history.quiz.api.v1.model.ErrorDB;
import com.adam.stan.history.quiz.api.v1.model.QuestionDB;
import com.adam.stan.history.quiz.service.common.exceptions.NotAddedQuestionsException;
import com.adam.stan.history.quiz.service.service.management.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class QuestionManagementController implements QuestionManagementApi {
    private final QuestionService service;

    public QuestionManagementController(QuestionService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ErrorDB>> addQuestions(List<QuestionDB> list) {
        try {
            service.addQuestions(list);
        } catch (NotAddedQuestionsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getErrors());
        }
        return ResponseEntity.accepted().body(Collections.emptyList());
    }
}
