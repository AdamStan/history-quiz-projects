package com.adam.stan.history.quiz.service.controller;

import com.adam.stan.history.quiz.api.v1.QuestionManagementApi;
import com.adam.stan.history.quiz.api.v1.model.QuestionDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionManagementController implements QuestionManagementApi {
    @Override
    public ResponseEntity<Void> addQuestions(List<QuestionDB> list) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
