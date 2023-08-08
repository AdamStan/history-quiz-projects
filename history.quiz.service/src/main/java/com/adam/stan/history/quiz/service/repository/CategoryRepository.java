package com.adam.stan.history.quiz.service.repository;

import com.adam.stan.history.quiz.service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByPeriodAndDetails(String period, String details);
}
