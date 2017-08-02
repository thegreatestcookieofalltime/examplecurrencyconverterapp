package com.gmail.zietkowski.filip.exchangeratessource.exceptions;

/**
 * The exception thrown if there's no data about the chosen exchange rate.
 * 
 * @author Filip Ziętkowski
 */
public class UnknownCurrencyException extends Exception {
    
    /**
     * The constructor method.
     * 
     * @param message The message thrown with the exception.
     */
    public UnknownCurrencyException(String message) {
        super(message);
    }
}
