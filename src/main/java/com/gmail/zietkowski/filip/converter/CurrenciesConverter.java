package com.gmail.zietkowski.filip.converter;

import com.gmail.zietkowski.filip.exchangeratessource.ExchangeRatesSource;

/**
 * The interface of the main class, responsible for asking for user input
 * and performing currency conversions.
 * 
 * @author Filip Ziętkowski
 */
public interface CurrenciesConverter {
    /**
     * Setter method for the exchange rates source.
     * 
     * @param exchangeRatesSource The new exchange rates source.
     */
    public void setExchangeRatesSource(ExchangeRatesSource exchangeRatesSource);
    
    /**
     * The main logic method, launched after the Spring dependencies injection.
     * 
     * @param args The command line arguments.
     */
    public void run(String[] args);
}
