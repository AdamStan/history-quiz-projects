package com.adam.stan.history.quiz.service.service.management

import com.adam.stan.history.quiz.api.v1.model.QuestionDB
import com.adam.stan.history.quiz.service.common.exceptions.NotAddedQuestionsException
import com.adam.stan.history.quiz.service.common.exceptions.NotEnoughItemsOnListException
import com.adam.stan.history.quiz.service.model.Answer
import com.adam.stan.history.quiz.service.model.AnswerType
import com.adam.stan.history.quiz.service.model.Category
import com.adam.stan.history.quiz.service.model.QuestionModel
import com.adam.stan.history.quiz.service.repository.AnswerRepository
import com.adam.stan.history.quiz.service.repository.CategoryRepository
import com.adam.stan.history.quiz.service.repository.QuestionModelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class QuestionServiceIT extends Specification {
    @Autowired
    private AnswerRepository answerRepository
    @Autowired
    private CategoryRepository categoryRepository
    @Autowired
    private QuestionModelRepository questionRepository
    @Autowired
    private QuestionServiceImpl questionService

    def setup() {
        Category cat1 = Category.builder()
                .details("Roman Empire")
                .period("Ancient")
                .build()
        cat1 = categoryRepository.save(cat1)

        Answer correctAnswer = Answer.builder()
                .category(cat1)
                .type(AnswerType.YEAR)
                .content("216 bc")
                .build()
        correctAnswer = answerRepository.save(correctAnswer)

        QuestionModel question = QuestionModel.builder()
                .correctAnswer(correctAnswer)
                .content("What year did the Battle of Cannae take place?")
                .build()
        questionRepository.save(question)
    }

    def cleanup() {
        questionRepository.deleteAll()
        answerRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    def "add question - existing category"() {
        given:
        var questionDB = new QuestionDB()
                .correctAnswer("d")
                .answerType("PERSON")
                .period("Ancient")
                .details("Roman Empire")
                .questionText("dd??")
        when:
        var error = questionService.addQuestion(questionDB)
        then:
        error == null
        questionRepository.findAll().size() == 2
        categoryRepository.findAll().size() == 1
    }

    def "add question - not existing category was created"() {
        given:
        var questionDB = new QuestionDB()
                .correctAnswer("d")
                .answerType("PERSON")
                .period("Ancient2")
                .details("Roman Empire")
                .questionText("dd??")
        when:
        var error = questionService.addQuestion(questionDB)
        then:
        error == null
        questionRepository.findAll().size() == 2
        categoryRepository.findAll().size() == 2
    }

    def "add questions - not existing category and correct question was rolled back when error"() {
        given:
        var questionsToDB = [
                new QuestionDB()
                        .correctAnswer("d")
                        .answerType("not_existing_type_xyz")
                        .period("Ancient2")
                        .details("Roman Empire")
                        .questionText("dd??"),
                new QuestionDB()
                        .correctAnswer("dd")
                        .answerType("PERSON")
                        .period("Ancient2")
                        .details("Roman Empire")
                        .questionText("dddd??")
        ]
        when:
        questionService.addQuestions(questionsToDB)
        then:
        def ex = thrown(NotAddedQuestionsException)
        verifyAll {
            ex.getErrors().size() == 1
            ex.getErrors().get(0).getQuestionText() == "dd??"
            ex.getErrors().get(0).getReason() == "Answer type 'not_existing_type_xyz' is not supported!"
            questionRepository.findAll().size() == 1
            categoryRepository.findAll().size() == 1
        }
    }
}
