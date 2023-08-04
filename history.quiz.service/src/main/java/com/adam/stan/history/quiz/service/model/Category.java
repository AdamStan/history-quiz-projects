package com.adam.stan.history.quiz.service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String period;
    private String details;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Answer> answers;
}
