package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.service.model.Category;
import com.adam.stan.history.quiz.service.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public List<String> getCategoryNames() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(Category::getPeriod).toList();
    }
}
