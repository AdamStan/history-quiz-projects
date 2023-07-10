package com.adam.stan.history.quiz.service.controller;

import com.adam.stan.history.quiz.api.v1.CategoryApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CategoryController implements CategoryApi {
    @Override
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
