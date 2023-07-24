package com.adam.stan.history.quiz.service.service;

import com.adam.stan.history.quiz.api.v1.model.Question;
import com.adam.stan.history.quiz.service.common.RandomItemsFromList;
import com.adam.stan.history.quiz.service.model.Answer;
import com.adam.stan.history.quiz.service.model.AnswerType;
import com.adam.stan.history.quiz.service.model.QuestionModel;
import com.adam.stan.history.quiz.service.repository.AnswerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
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
        AnswerType type = question.getCorrectAnswer().getType();
        // TODO add category to search
        // Category cat = question.getCorrectAnswer().getCategory();
        List<Answer> answers = answerRepository.findByAnswerType(type);

        RandomItemsFromList<Answer> itemsGenerator = new RandomItemsFromList<>(amountOfChoices, answers);
        List<Answer> chosenAnswers = itemsGenerator.getRandomItems();
        if (checkIfNotContainsCorrectAnswer(chosenAnswers, question.getCorrectAnswer())) {
            chosenAnswers.remove(ThreadLocalRandom.current().nextInt(chosenAnswers.size()));
            chosenAnswers.add(question.getCorrectAnswer());
        }
        chosenAnswers.stream()
                .map(Answer::getContent)
                .forEach(jsonObject::addOtherAnswersItem);
        return jsonObject;
    }

    private boolean checkIfNotContainsCorrectAnswer(List<Answer> chosenAnswers, Answer correctAnswer) {
        return chosenAnswers.stream().noneMatch(ans -> ans.getContent().equals(correctAnswer.getContent()));
    }

}
