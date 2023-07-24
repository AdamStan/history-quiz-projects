package com.adam.stan.history.quiz.service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String period;
    private String details;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Answer> answers;
}
