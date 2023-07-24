package com.adam.stan.history.quiz.service.repository;

import com.adam.stan.history.quiz.service.model.Answer;
import com.adam.stan.history.quiz.service.model.AnswerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Long, Answer> {

    List<Answer> findByAnswerType(AnswerType type);
}
