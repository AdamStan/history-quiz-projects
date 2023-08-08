package com.adam.stan.history.quiz.service.model;

public enum AnswerType {
    YEAR("Only year"),
    DATE("Day, month and year"),
    PERSON("A king, inventor or somebody else"),
    YEAR_PERIOD("From year to year"),
    DATE_PERIOD("From dd-MM-yyyy to dd-MM-yyyy");
    private final String description;

    AnswerType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name() + "{description='" + description + "'}";
    }

    public static AnswerType from(String typeName) {
        for (AnswerType type : values()) {
            if (type.name().equals(typeName)) {
                return type;
            }
        }
        return null;
    }
}
