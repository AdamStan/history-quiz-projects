package com.adam.stan.history.quiz.service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer correctAnswer;
}
