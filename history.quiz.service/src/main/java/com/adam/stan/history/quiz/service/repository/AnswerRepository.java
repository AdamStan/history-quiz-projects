package com.adam.stan.history.quiz.service.repository;

import com.adam.stan.history.quiz.service.model.Answer;
import com.adam.stan.history.quiz.service.model.AnswerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByType(AnswerType type);
    //https://stackoverflow.com/questions/49375051/spring-repository-interface-find-record-using-foreign-key
    List<Answer> findByTypeAndCategory_Id(AnswerType type, Long categoryId);
    Optional<Answer> findByContentAndTypeAndCategory_Id(String content, AnswerType type, Long categoryId);
}
