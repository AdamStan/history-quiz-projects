package com.adam.stan.history.quiz.service.service.management


import com.adam.stan.history.quiz.api.v1.model.QuestionDB
import com.adam.stan.history.quiz.service.model.Answer
import com.adam.stan.history.quiz.service.model.Category
import com.adam.stan.history.quiz.service.model.QuestionModel
import com.adam.stan.history.quiz.service.repository.AnswerRepository
import com.adam.stan.history.quiz.service.repository.CategoryRepository
import com.adam.stan.history.quiz.service.repository.QuestionModelRepository
import spock.lang.Specification

class QuestionServiceImplTest extends Specification {
    private QuestionServiceImpl questionService
    private var questionModelRepository
    private var answerRepository
    private var categoryRepository

    def setup() {
        questionModelRepository = Mock(QuestionModelRepository)
        answerRepository = Mock(AnswerRepository)
        categoryRepository = Mock(CategoryRepository)
        questionService = new QuestionServiceImpl(questionModelRepository, answerRepository, categoryRepository)
    }

    def "add question - answer and category are found in DB"() {
        given:
        questionModelRepository.findByContentAndCorrectAnswer_Id("dd??", 1) >> Optional.empty()
        var questionDB = new QuestionDB()
                .correctAnswer("d")
                .answerType("PERSON")
                .period("12")
                .details("ab")
                .questionText("dd??")
        when:
        var result = questionService.addQuestion(questionDB)
        then:
        result == null
        verifyAll {
            1 * answerRepository.findByContentAndTypeAndCategory_Id(_, _, _) >> Optional.of(Answer.builder().id(1).build())
            1 * categoryRepository.findByPeriodAndDetails(_, _) >> Optional.of(Category.builder().id(1).build())
            0 * categoryRepository.save(_)
            0 * answerRepository.save(_)
            1 * questionModelRepository.save(_)
        }
    }

    def "add question - answer and category are not found in DB"() {
        given:
        questionModelRepository.findByContentAndCorrectAnswer_Id("dd??", 1) >> Optional.empty()
        var questionDB = new QuestionDB()
                .correctAnswer("d")
                .answerType("PERSON")
                .period("12")
                .details("ab")
                .questionText("dd??")
        when:
        var error = questionService.addQuestion(questionDB)
        then:
        error == null
        verifyAll {
            1 * answerRepository.findByContentAndTypeAndCategory_Id(_, _, _) >> Optional.empty()
            1 * categoryRepository.findByPeriodAndDetails(_, _) >> Optional.empty()
            1 * categoryRepository.save(_ as Category) >> Category.builder().id(1).build()
            1 * answerRepository.save(_ as Answer) >> Answer.builder().id(1).build()
            1 * questionModelRepository.save(_)
        }
    }

    def "add question - question exists in DB - error"() {
        given:
        var questionDB = new QuestionDB()
                .correctAnswer("d")
                .answerType("PERSON")
                .period("12")
                .details("ab")
                .questionText("dd??")
        when:
        var error = questionService.addQuestion(questionDB)
        then:
        error != null
        verifyAll {
            1 * questionModelRepository.findByContentAndCorrectAnswer_Id("dd??", 1) >> Optional.of(QuestionModel.builder().build())
            1 * answerRepository.findByContentAndTypeAndCategory_Id(_, _, _) >> Optional.empty()
            1 * categoryRepository.findByPeriodAndDetails(_, _) >> Optional.empty()
            1 * categoryRepository.save(_ as Category) >> Category.builder().id(1).build()
            1 * answerRepository.save(_ as Answer) >> Answer.builder().id(1).build()
            0 * questionModelRepository.save(_)
        }
    }

    def "add question - answer's type is missing - error"() {
        given:
        var questionDB = new QuestionDB()
                .correctAnswer("d")
                .answerType("type_not_exists_xxx")
                .period("12")
                .details("ab")
                .questionText("dd??")
        when:
        var error = questionService.addQuestion(questionDB)
        then:
        error != null
        verifyAll {
            1 * categoryRepository.findByPeriodAndDetails(_, _) >> Optional.empty()
            1 * categoryRepository.save(_ as Category) >> Category.builder().id(1).build()
            0 * answerRepository.save(_ as Answer)
            0 * questionModelRepository.save(_)
        }
    }
}
