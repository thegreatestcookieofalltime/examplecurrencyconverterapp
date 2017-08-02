package com.gmail.zietkowski.filip.exchangeratessource;

import org.junit.Before;

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
    
    
}
