package com.adam.stan.history.quiz.service.service.management;

import com.adam.stan.history.quiz.api.v1.model.ErrorDB;
import com.adam.stan.history.quiz.api.v1.model.QuestionDB;
import com.adam.stan.history.quiz.service.common.exceptions.NotAddedQuestionsException;
import com.adam.stan.history.quiz.service.model.Answer;
import com.adam.stan.history.quiz.service.model.AnswerType;
import com.adam.stan.history.quiz.service.model.Category;
import com.adam.stan.history.quiz.service.model.QuestionModel;
import com.adam.stan.history.quiz.service.repository.AnswerRepository;
import com.adam.stan.history.quiz.service.repository.CategoryRepository;
import com.adam.stan.history.quiz.service.repository.QuestionModelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionModelRepository questionModelRepository;
    private final AnswerRepository answerRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ErrorDB addQuestion(QuestionDB questionDB) {
        Optional<Category> cat = categoryRepository.findByPeriodAndDetails(questionDB.getPeriod(), questionDB.getDetails());
        Category cat1 = cat.orElseGet(() -> categoryRepository.save(Category.builder()
                        .period(questionDB.getPeriod())
                        .details(questionDB.getDetails())
                .build()));

        AnswerType type = AnswerType.from(questionDB.getAnswerType());
        if (type == null) {
            return new ErrorDB().questionText(questionDB.getQuestionText()).reason(String.format("Answer type '%s' is not supported!", questionDB.getAnswerType()));
        }

        Optional<Answer> answer = answerRepository.findByContentAndTypeAndCategory_Id(questionDB.getCorrectAnswer(), type, cat1.getId());
        Answer ans1 = answer.orElseGet(() -> answerRepository.save(
                Answer.builder()
                        .content(questionDB.getCorrectAnswer())
                        .type(type)
                        .category(cat1)
                        .build()
        ));

        Optional<QuestionModel> question = questionModelRepository.findByContentAndCorrectAnswer_Id(questionDB.getQuestionText(), ans1.getId());
        if (question.isPresent()) {
            return new ErrorDB().questionText(questionDB.getQuestionText()).reason("Such question exists in the database!");
        }

        QuestionModel model = QuestionModel.builder()
                .correctAnswer(ans1)
                .content(questionDB.getQuestionText())
                .build();
        questionModelRepository.save(model);

        return null;
    }

    @Override
    @Transactional(rollbackOn = NotAddedQuestionsException.class)
    public void addQuestions(List<QuestionDB> questions) {
        List<ErrorDB> errors = new ArrayList<>();
        for (QuestionDB qdb : questions) {
            ErrorDB errorDB = addQuestion(qdb);
            if (errorDB != null) errors.add(errorDB);
        }
        if (!errors.isEmpty()) {
            throw new NotAddedQuestionsException("Some questions weren't added, check errors", errors);
        }
    }
}
