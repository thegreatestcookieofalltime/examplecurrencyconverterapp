package com.gmail.zietkowski.filip.exchangeratessource;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ExchangeRatesSource implementation.
 * 
 * @author Filip Ziętkowski
 */
public class ExchangeRatesSourceTest {
    /**
     * The tested class.
     */
    private ExchangeRatesSource exchangeRatesSource;
    
    /**
     * The set up method, executed before every test.
     */
    @Before
    public void before() {
        exchangeRatesSource = new ExchangeRatesSourceImpl();
    }
    
    /**
     * Test of the getStringListOfAvailableExchangeRates method.
     */
    @Test
    public void 
    getStringListOfAvailableExchangeRatesShouldReturnProperExchangeRatesTest() {
        assertEquals("Proper exchange rates should be returned",
                     exchangeRatesSource
                     .getStringListOfAvailableExchangeRates(),
                     "PLN -> USD\nPLN -> EUR\nPLN -> GBP\nUSD -> PLN\n"
                     + "USD -> EUR\nUSD -> GBP\nEUR -> PLN\nEUR -> USD\n"
                     + "EUR -> GBP\nGBP -> PLN\nGBP -> USD\nGBP -> EUR\n");
    }
}
