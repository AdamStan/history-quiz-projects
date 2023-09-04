package com.adam.stan.history.quiz.service.controller

import com.adam.stan.history.quiz.service.AbstractIT
import com.adam.stan.history.quiz.service.model.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
class CategoryControllerIT extends AbstractIT {

    @Autowired
    private MockMvc mvc

    def setup() {
        categoryRepository.save(Category.builder().period("Middle Ages").build())
        categoryRepository.save(Category.builder().period("Antiquity").build())
        categoryRepository.save(Category.builder().period("Modern").build())
    }

    def "when get is performed then the response has status 200 and content is 'Hello world!'"() {
        expect:
        mvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "[\"Middle Ages\",\"Antiquity\",\"Modern\"]"
    }
}
