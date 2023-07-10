package com.adam.stan.history.quiz.service.model;

public enum AnswerType {
    YEAR("Only year"),
    DATE("Day, month and year"),
    PERSON("A king, inventor or somebody else");
    private final String description;

    AnswerType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name() + "{description='" + description + "'}";
    }
}
