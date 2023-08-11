package com.adam.stan.history.quiz.service

import com.adam.stan.history.quiz.service.model.Answer
import com.adam.stan.history.quiz.service.model.AnswerType
import com.adam.stan.history.quiz.service.model.Category
import com.adam.stan.history.quiz.service.model.QuestionModel
import com.adam.stan.history.quiz.service.service.QuestionPreparationImpl
import org.springframework.beans.factory.annotation.Autowired

class QuestionPreparationIT extends AbstractIT {
    @Autowired
    private QuestionPreparationImpl questionPreparation

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

        answerRepository.save(Answer.builder().content("264 bc").type(AnswerType.YEAR).category(cat1).build())
        answerRepository.save(Answer.builder().content("202 bc").type(AnswerType.YEAR).category(cat1).build())
        answerRepository.save(Answer.builder().content("71 bc").type(AnswerType.YEAR).category(cat1).build())
    }

    def "test generating question with database"() {
        given:
        var question = questionRepository.findByContent("What year did the Battle of Cannae take place?")
        when:
        var result = questionPreparation.createQuestion(question, 4)
        then:
        verifyAll {
            result.getText() == question.getContent()
            result.getCorrectAnswer() == question.getCorrectAnswer().getContent()
            !result.getOtherAnswers().contains(question.getCorrectAnswer().getContent())
            result.getOtherAnswers().size() == 3
        }
    }
}
