package com.gmail.zietkowski.filip.exchangeratessource.data;

import java.math.BigDecimal;

/**
 * The class responsible for keeping the data about a single exchange rate.
 * 
 * @author Filip Ziętkowski
 */
public class ExchangeRate {
    /**
     * The source currency.
     */
    private final Currency sourceCurrency;
    
    /**
     * The target currency.
     */
    private final Currency targetCurrency;
    
    /**
     * The exchange rate.
     */
    private final BigDecimal rate;
    
    /**
     * The constructor method.
     * 
     * @param sourceCurrency The source currency.
     * @param targetCurrency The target currency.
     * @param rate The exchange rate.
     */
    public ExchangeRate(Currency sourceCurrency, Currency targetCurrency,
                        BigDecimal rate){
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }
    
    /**
     * The source currency getter method.
     * 
     * @return The source currency.
     */
    public Currency getSourceCurrency() {
        return sourceCurrency;
    }
    
    /**
     * The target currency getter method.
     * 
     * @return The target currency.
     */
    public Currency getTargetCurrency() {
        return targetCurrency;
    }
    
    /**
     * The exchange rate getter method.
     * 
     * @return The exchange rate.
     */
    public BigDecimal getRate() {
        return rate;
    }
}
