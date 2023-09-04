package com.adam.stan.history.quiz.service.service

import com.adam.stan.history.quiz.service.model.Category
import com.adam.stan.history.quiz.service.repository.CategoryRepository
import spock.lang.Specification

class CategoryServiceImplTest extends Specification {
    private CategoryServiceImpl categoryService;

    void setup() {
        var repo = Mock(CategoryRepository)
        repo.findAll() >> List.of(
                Category.builder().period("Middle Ages").build(),
                Category.builder().period("Antiquity").build(),
                Category.builder().period("Modern").build()
        )
        categoryService = new CategoryServiceImpl(repo)
    }

    def "GetCategoryNames"() {
        when:
        var result = categoryService.getCategoryNames()
        then:
        verifyAll {
            result.size() == 3
            result.contains("Middle Ages")
            result.contains("Antiquity")
            result.contains("Modern")
        }
    }
}
