package com.gmail.zietkowski.filip.exchangeratessource;

import com.gmail.zietkowski.filip.exchangeratessource.data.Currency;
import com.gmail.zietkowski.filip.exchangeratessource.data.ExchangeRate;
import com.gmail.zietkowski.filip.exchangeratessource.exceptions.UnknownCurrencyException;
import java.math.BigDecimal;
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
     * A failed assertion message which appears many times.
     */
    public static final String IMPROPER_EXCHANGE_RATES = "Proper exchange rates"
                                                         + " should be "
                                                         + "returned";
    
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
                     "PLN -> USD\nPLN -> EUR\nPLN -> GBP\nUSD -> PLN\n"
                     + "USD -> EUR\nUSD -> GBP\nEUR -> PLN\nEUR -> USD\n"
                     + "EUR -> GBP\nGBP -> PLN\nGBP -> USD\nGBP -> EUR\n",
                     exchangeRatesSource
                     .getStringListOfAvailableExchangeRates());
    }
    
    /**
     * Test of the getExchangeRate method.
     * 
     * @throws UnknownCurrencyException The exception throwed
     * by the getExchangeRate method.
     */
    @Test
    public void getExchangeRateShouldReturnProperExchangeRatesTest()
                throws UnknownCurrencyException {
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(0.28), 
                     exchangeRatesSource.getExchangeRate("PLN", "USD"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(0.23),
                     exchangeRatesSource.getExchangeRate("PLN", "EUR"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(0.21),
                     exchangeRatesSource.getExchangeRate("PLN", "GBP"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(3.60),
                     exchangeRatesSource.getExchangeRate("USD", "PLN"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(0.85),
                     exchangeRatesSource.getExchangeRate("USD", "EUR"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(0.76),
                     exchangeRatesSource.getExchangeRate("USD", "GBP"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(4.26),
                     exchangeRatesSource.getExchangeRate("EUR", "PLN"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(1.18),
                     exchangeRatesSource.getExchangeRate("EUR", "USD"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(0.89),
                     exchangeRatesSource.getExchangeRate("EUR", "GBP"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(4.75),
                     exchangeRatesSource.getExchangeRate("GBP", "PLN"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(1.32),
                     exchangeRatesSource.getExchangeRate("GBP", "USD"));
        assertEquals(IMPROPER_EXCHANGE_RATES,
                     BigDecimal.valueOf(1.12),
                     exchangeRatesSource.getExchangeRate("GBP", "EUR"));
    }
    
    /**
     * Test of the getExchangeRate method when given improper input values.
     */
    @Test
    public void getExchangeRateShouldThrowAnExceptionOnImproperValuesTest() {
        int catchedExceptions = 0;
        
        try {
            exchangeRatesSource.getExchangeRate("ASDF", "USD");
        } catch(UnknownCurrencyException ex) {
            catchedExceptions++;
        }
        try {
            exchangeRatesSource.getExchangeRate("PLN", "ASDF");
        } catch(UnknownCurrencyException ex) {
            catchedExceptions++;
        }
        try {
            exchangeRatesSource.getExchangeRate("ASDF", "FDSA");
        } catch(UnknownCurrencyException ex) {
            catchedExceptions++;
        }
        assertEquals("An exception should be thrown when given improper"
                     + " input values", 3, catchedExceptions);
    }
}
