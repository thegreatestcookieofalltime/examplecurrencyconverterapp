package com.gmail.zietkowski.filip.converter;

import com.gmail.zietkowski.filip.exchangeratessource.ExchangeRatesSource;
import com.gmail.zietkowski.filip.exchangeratessource.exceptions.UnknownCurrencyException;
import java.math.BigDecimal;

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
     * The currency conversion method.
     * 
     * @param sourceCurrency The source currency.
     * @param targetCurrency The target currency.
     * @param amount The amount to be converted.
     * 
     * @return The amount of target currency possible to get for the source one.
     * 
     * @throws UnknownCurrencyException The exception thrown when there's
     * no data about the chosen currency exchange rates.
     */
    public BigDecimal convertCurrency(String sourceCurrency,
                                      String targetCurrency,
                                      BigDecimal amount)
                      throws UnknownCurrencyException;
    
    /**
     * The main logic method, launched after the Spring dependencies injection.
     * 
     * @param args The command line arguments.
     */
    public void run(String[] args);
}
