package com.adam.stan.history.quiz.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApplicationTests extends Specification {
    @Autowired
    private Application application

    def "content loads"() {
        expect:
        application
    }

}
