package com.adam.stan.history.quiz.service.controller;

import com.adam.stan.history.quiz.api.v1.CategoryApi;
import com.adam.stan.history.quiz.service.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {
    private final CategoryService service;

    @Override
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok().body(service.getCategoryNames());
    }
}
