package com.gmail.zietkowski.filip.converter;

import com.gmail.zietkowski.filip.exchangeratessource.ExchangeRatesSource;
import com.gmail.zietkowski.filip.exchangeratessource.ExchangeRatesSourceImpl;
import com.gmail.zietkowski.filip.exchangeratessource.exceptions.UnknownCurrencyException;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the CurrenciesConverter implementation.
 * 
 * @author Filip Ziętkowski
 */
public class CurrenciesConverterTest {
    /**
     * The exchange rates source mock.
     */
    private ExchangeRatesSource exchangeRatesSource;
    
    /**
     * The tested class.
     */
    private CurrenciesConverter currenciesConverter;
    
    /**
     * The set up method, executed before every test.
     * 
     * @throws UnknownCurrencyException The exception throwed by the mocked
     * method.
     */
    @Before
    public void before() throws UnknownCurrencyException {
        exchangeRatesSource = mock(ExchangeRatesSourceImpl.class);
        
        when(exchangeRatesSource.getExchangeRate("USD", "PLN"))
                                .thenReturn(BigDecimal.valueOf(2));
        when(exchangeRatesSource.getExchangeRate("PLN", "USD"))
                                .thenReturn(BigDecimal.valueOf(0.5));
        when(exchangeRatesSource.getExchangeRate("PLN", "EUR"))
                                .thenReturn(BigDecimal.valueOf(0));
        when(exchangeRatesSource.getExchangeRate("ASDF", "USD"))
                                .thenThrow(UnknownCurrencyException.class);
        when(exchangeRatesSource.getExchangeRate("PLN", "ASDF"))
                                .thenThrow(UnknownCurrencyException.class);
        when(exchangeRatesSource.getExchangeRate("ASDF", "FDSA"))
                                .thenThrow(UnknownCurrencyException.class);
        
        currenciesConverter = new CurrenciesConverterImpl();
        currenciesConverter.setExchangeRatesSource(exchangeRatesSource);
    }
    
    /**
     * Test of the convertCurrency method when given the proper input values.
     */
    @Test
    public void properValuesShouldGiveProperConvertCurrencyResultsTest() {
        BigDecimal result;
        
        try {
            result = currenciesConverter.convertCurrency("USD", "PLN",
                                             BigDecimal.valueOf(5));
            verify(exchangeRatesSource, times(1)).getExchangeRate(
                                        ArgumentMatchers.eq("USD"),
                                        ArgumentMatchers.eq("PLN"));
            assertEquals(BigDecimal.valueOf(10), result);
            
            result = currenciesConverter.convertCurrency("PLN", "USD",
                                             BigDecimal.valueOf(7));
            verify(exchangeRatesSource, times(1)).getExchangeRate(
                                        ArgumentMatchers.eq("PLN"),
                                        ArgumentMatchers.eq("USD"));
            assertEquals(BigDecimal.valueOf(3.5), result);
            
            result = currenciesConverter.convertCurrency("PLN", "EUR",
                                             BigDecimal.valueOf(3));
            verify(exchangeRatesSource, times(1)).getExchangeRate(
                                        ArgumentMatchers.eq("PLN"),
                                        ArgumentMatchers.eq("EUR"));
            assertEquals(BigDecimal.valueOf(0), result);
        } catch(UnknownCurrencyException ex) {
            fail("No exception should be thrown when given the proper input"
                 + " values");
        }
    }
    
    /**
     * Test of the convertCurrency method when given improper input values.
     * 
     * @throws UnknownCurrencyException The exception throwed by the mocked
     * method.
     */
    @Test
    public void improperValuesShouldThrowAnExceptionTest()
                throws UnknownCurrencyException {
        int catchedExceptions = 0;
        
        try {
            currenciesConverter.convertCurrency("ASDF", "USD",
                                                    BigDecimal.valueOf(5));
        } catch(UnknownCurrencyException ex) {
            catchedExceptions++;
        }
        verify(exchangeRatesSource, times(1)).getExchangeRate(
                                              ArgumentMatchers.eq("ASDF"),
                                              ArgumentMatchers.eq("USD"));
        try {
            currenciesConverter.convertCurrency("PLN", "ASDF",
                                                    BigDecimal.valueOf(7));
        } catch(UnknownCurrencyException ex) {
            catchedExceptions++;
        }
        verify(exchangeRatesSource, times(1)).getExchangeRate(
                                              ArgumentMatchers.eq("PLN"),
                                              ArgumentMatchers.eq("ASDF"));
        try {
            currenciesConverter.convertCurrency("ASDF", "FDSA",
                                                    BigDecimal.valueOf(3));
        } catch(UnknownCurrencyException ex) {
            catchedExceptions++;
        }
        verify(exchangeRatesSource, times(1)).getExchangeRate(
                                              ArgumentMatchers.eq("ASDF"),
                                              ArgumentMatchers.eq("FDSA"));
        assertEquals("An exception should be thrown when given improper"
                     + " input values", 3, catchedExceptions);
    }
}
