package com.adam.stan.history.quiz.service

import com.adam.stan.history.quiz.service.repository.AnswerRepository
import com.adam.stan.history.quiz.service.repository.CategoryRepository
import com.adam.stan.history.quiz.service.repository.QuestionModelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AbstractIT extends Specification {
    @Autowired
    protected AnswerRepository answerRepository
    @Autowired
    protected CategoryRepository categoryRepository
    @Autowired
    protected QuestionModelRepository questionRepository

    def cleanup() {
        questionRepository.deleteAll()
        answerRepository.deleteAll()
        categoryRepository.deleteAll()
    }
}
