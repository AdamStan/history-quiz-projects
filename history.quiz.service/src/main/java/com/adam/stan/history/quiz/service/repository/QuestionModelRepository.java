package com.adam.stan.history.quiz.service.repository;

import com.adam.stan.history.quiz.service.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionModelRepository extends JpaRepository<QuestionModel, Long> {

    QuestionModel findByContent(String content);

    Optional<QuestionModel> findByContentAndAnswer_Id(String content, Long answerId);

}
