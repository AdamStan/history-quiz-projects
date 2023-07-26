package com.adam.stan.history.quiz.service.repository;

import com.adam.stan.history.quiz.service.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionModelRepository extends JpaRepository<QuestionModel, Long> {

}
