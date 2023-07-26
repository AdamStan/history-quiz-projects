package com.adam.stan.history.quiz.service.service

import com.adam.stan.history.quiz.service.common.exceptions.NotEnoughItemsOnListException
import com.adam.stan.history.quiz.service.model.Answer
import com.adam.stan.history.quiz.service.model.AnswerType
import com.adam.stan.history.quiz.service.model.QuestionModel
import com.adam.stan.history.quiz.service.repository.AnswerRepository
import spock.lang.Specification

class QuestionPreparationImplTest extends Specification {
    private AnswerRepository repository;
    private QuestionPreparationImpl questionPreparation;

    def setup() {
        repository = Mock(AnswerRepository.class);
        questionPreparation = new QuestionPreparationImpl(repository);
    }

    def "createQuestion"() {
        given:
        Answer correctAnswer = Answer.builder()
                .content("Jeremi")
                .type(AnswerType.PERSON)
                .build();
        repository.findByAnswerType(AnswerType.PERSON) >> List.of(
                Answer.builder().content("Jan").build(),
                Answer.builder().content("Michał").build(),
                Answer.builder().content("Łukasz").build(),
                Answer.builder().content("Jarosław").build(),
                Answer.builder().content("Stanisław").build(),
                Answer.builder().content("Maciej").build(),
                correctAnswer
        )
        QuestionModel model = QuestionModel.builder()
                .content("Najlepsze imie dla syna?")
                .correctAnswer(correctAnswer)
                .build();
        when:
        var result = questionPreparation.createQuestion(model, 4);
        then:
        verifyAll {
            result.getText() == model.getContent()
            result.getCorrectAnswer() == model.getCorrectAnswer().getContent()
            !result.getOtherAnswers().contains("Jeremi")
            result.getOtherAnswers().size() == 3
        }
    }

    def "createQuestion throws an exception when not enough answers available"() {
        given:
        Answer correctAnswer = Answer.builder()
                .content("Jeremi")
                .type(AnswerType.PERSON)
                .build();
        repository.findByAnswerType(AnswerType.PERSON) >> List.of(
                Answer.builder().content("Jan").build(),
                Answer.builder().content("Michał").build(),
                correctAnswer
        )
        QuestionModel model = QuestionModel.builder()
                .content("Najlepsze imie dla syna?")
                .correctAnswer(correctAnswer)
                .build();
        when:
        questionPreparation.createQuestion(model, 4);
        then:
        def ex = thrown(NotEnoughItemsOnListException)
        ex.message == "Too less elements on the list. There is 3 elements, but we need at least 4 elements"
    }
}