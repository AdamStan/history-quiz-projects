package com.adam.stan.history.quiz.service.common.exceptions;

/**
 * Should be thrown by {@link RandomItemsFromList}
 *
 * @author adam
 *
 */
public class NotEnoughItemsOnListException extends RuntimeException {
    private static final long serialVersionUID = 7483649114262446680L;

    private final int itemsOnList;
    private final int howManyItemsToChoose;

    public NotEnoughItemsOnListException(String message, int itemsOnList, int howManyItemsToChoose) {
        super(message + String.format(" There is %d elements, but we need %d elements", itemsOnList, howManyItemsToChoose));
        this.itemsOnList = itemsOnList;
        this.howManyItemsToChoose = howManyItemsToChoose;
    }

    public int getItemsOnList() {
        return itemsOnList;
    }

    public int getHowManyItemsToChoose() {
        return howManyItemsToChoose;
    }

}