package com.gmail.zietkowski.filip.exchangeratessource;

import com.gmail.zietkowski.filip.exchangeratessource.exceptions.UnknownCurrencyException;
import java.math.BigDecimal;

/**
 * The interface for the class responsible for retrieving the exchange rates.
 * 
 * @author Filip Ziętkowski
 */
public interface ExchangeRatesSource {
    /**
     * The method responsible for retrieving available exchange rates.
     * 
     * @return The available exchange rates.
     */
    public String getAvailableExchangeRates();
    
    /**
     * The method responsible for retrieving an exchange rate.
     * 
     * @param sourceCurrency The source currency.
     * @param targetCurrency The target currency.
     * @return The exchange rate.
     * @throws UnknownCurrencyException Thrown if there is no data about
     * the chosen exchange rate.
     */
    public BigDecimal getExchangeRate(String sourceCurrency,
                                      String targetCurrency)
                      throws UnknownCurrencyException;
}
