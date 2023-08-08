package com.adam.stan.history.quiz.service.common.exceptions;

import com.adam.stan.history.quiz.api.v1.model.ErrorDB;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class NotAddedQuestionsException extends RuntimeException {

    private final transient List<ErrorDB> errors;

    public NotAddedQuestionsException(String message, List<ErrorDB> errors) {
        super(message);
        this.errors = Collections.unmodifiableList(errors);
    }

}
