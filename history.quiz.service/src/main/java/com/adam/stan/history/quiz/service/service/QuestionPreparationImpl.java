package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.common.RandomItemsFromList;
import com.adam.stan.history.quiz.service.model.Answer;
import com.adam.stan.history.quiz.service.model.AnswerType;
import com.adam.stan.history.quiz.service.model.Category;
import com.adam.stan.history.quiz.service.model.QuestionModel;
import com.adam.stan.history.quiz.service.repository.AnswerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class QuestionPreparationImpl implements QuestionPreparation {

    private final AnswerRepository answerRepository;

    public QuestionPreparationImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Question createQuestion(QuestionModel question, int amountOfChoices) {
        Question jsonObject = new Question();
        jsonObject.setText(question.getContent());
        jsonObject.setCorrectAnswer(question.getCorrectAnswer().getContent());
        AnswerType type = question.getCorrectAnswer().getType();
        Category cat = question.getCorrectAnswer().getCategory();
        List<Answer> answers = answerRepository.findByTypeAndCategory_Id(type, cat.getId());

        RandomItemsFromList<Answer> itemsGenerator = new RandomItemsFromList<>(amountOfChoices, answers);
        List<Answer> chosenAnswers = itemsGenerator.getRandomItems();
        Optional<Answer> correctAnswer = chosenAnswers.stream().filter(e -> e.getContent().equals(question.getCorrectAnswer().getContent())).findFirst();

        if (correctAnswer.isPresent()) {
            chosenAnswers.remove(correctAnswer.get());
        } else {
            chosenAnswers.remove(ThreadLocalRandom.current().nextInt(chosenAnswers.size()));
        }

        chosenAnswers.stream()
                .map(Answer::getContent)
                .forEach(jsonObject::addOtherAnswersItem);
        return jsonObject;
    }

}
