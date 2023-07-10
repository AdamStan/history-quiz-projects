package com.adam.stan.history.quiz.service.common;


import com.adam.stan.history.quiz.service.common.exceptions.NotEnoughItemsOnListException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomItemsFromList<E> {
    private final int numberOfItems;
    private final List<E> elements;

    public RandomItemsFromList(int numberOfElements, List<E> objects) {
        numberOfItems = numberOfElements;
        elements = objects;
    }

    public List<E> getRandomItems() {
        if (elements.size() < numberOfItems) {
            throw new NotEnoughItemsOnListException("Too less elements on the list.", elements.size(), numberOfItems);
        }
        List<E> aFewOfThem = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < numberOfItems; i++) {
            int index = random.nextInt(elements.size());
            E element = elements.get(index);
            elements.remove(element);
            aFewOfThem.add(element);
        }
        return aFewOfThem;
    }
}

