package com.gmail.zietkowski.filip.exchangeratessource;

import com.gmail.zietkowski.filip.exchangeratessource.exceptions.UnknownCurrencyException;
import com.gmail.zietkowski.filip.exchangeratessource.data.Currency;
import com.gmail.zietkowski.filip.exchangeratessource.data.ExchangeRate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for retrieving the exchange rates.
 * 
 * @author Filip Ziętkowski
 */
public class ExchangeRatesSourceImpl implements ExchangeRatesSource {
    /**
     * The available exchange rates.
     */
    List <ExchangeRate> exchangeRates;
    
    /**
     * The default constructor.
     */
    public ExchangeRatesSourceImpl() {
        /*
            Of course in real-life app, the rates should be retrieved
            from somewhere instead of being hardcoded here. The only
            reason it's done like this is because it's an example app.
            Changing this shouldn't be a problem though, because
            this class is a separate Spring Bean, which means we can
            easily modify it without influencing the classes which use it.
        */
        
        exchangeRates = new ArrayList <>();
        exchangeRates.add(new ExchangeRate(Currency.PLN, Currency.USD,
                          BigDecimal.valueOf(0.28)));
        exchangeRates.add(new ExchangeRate(Currency.PLN, Currency.EUR,
                          BigDecimal.valueOf(0.23)));
        exchangeRates.add(new ExchangeRate(Currency.PLN, Currency.GBP,
                          BigDecimal.valueOf(0.21)));
        exchangeRates.add(new ExchangeRate(Currency.USD, Currency.PLN,
                          BigDecimal.valueOf(3.60)));
        exchangeRates.add(new ExchangeRate(Currency.USD, Currency.EUR,
                          BigDecimal.valueOf(0.85)));
        exchangeRates.add(new ExchangeRate(Currency.USD, Currency.GBP,
                          BigDecimal.valueOf(0.76)));
        exchangeRates.add(new ExchangeRate(Currency.EUR, Currency.PLN,
                          BigDecimal.valueOf(4.26)));
        exchangeRates.add(new ExchangeRate(Currency.EUR, Currency.USD,
                          BigDecimal.valueOf(1.18)));
        exchangeRates.add(new ExchangeRate(Currency.EUR, Currency.GBP,
                          BigDecimal.valueOf(0.89)));
        exchangeRates.add(new ExchangeRate(Currency.GBP, Currency.PLN,
                          BigDecimal.valueOf(4.75)));
        exchangeRates.add(new ExchangeRate(Currency.GBP, Currency.USD,
                          BigDecimal.valueOf(1.32)));
        exchangeRates.add(new ExchangeRate(Currency.GBP, Currency.EUR,
                          BigDecimal.valueOf(1.12)));
        
        System.out.println("\n#### Exchange rates source initialized. ####\n");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAvailableExchangeRates() {
        StringBuilder ret = new StringBuilder();
        for (ExchangeRate eR: exchangeRates) {
            ret.append(eR.getSourceCurrency());
            ret.append(" -> ");
            ret.append(eR.getTargetCurrency());
            ret.append("\n");
        }
        return ret.toString();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getExchangeRate(String sourceCurrency,
                                      String targetCurrency)
                      throws UnknownCurrencyException {
        for (ExchangeRate eR: exchangeRates) {
            if (eR.getSourceCurrency().toString()
                .equalsIgnoreCase(sourceCurrency)
                && eR.getTargetCurrency().toString()
                .equalsIgnoreCase(targetCurrency)) {
                return eR.getRate();
            }
        }
        throw new UnknownCurrencyException("No data about the exchange rate"
                + " of " + sourceCurrency + "to" + targetCurrency
                + " has been found");
    }
}
