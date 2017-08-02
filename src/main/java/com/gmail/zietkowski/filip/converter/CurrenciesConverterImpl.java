package com.gmail.zietkowski.filip.converter;

import com.gmail.zietkowski.filip.exchangeratessource.exceptions
       .UnknownCurrencyException;
import com.gmail.zietkowski.filip.exchangeratessource.ExchangeRatesSource;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The main class, responsible for asking for user input and performing
 * currency conversions.
 * 
 * @author Filip Ziętkowski
 */
public class CurrenciesConverterImpl implements CurrenciesConverter {
    /**
     * The error code for a situation when user uses an unimplemented currency.
     */
    public static final int ERROR_CODE_UNKNOWN_CURRENCY = 1;
    
    /**
     * The logger reference.
     */
    private static final Logger LOGGER = Logger.getLogger(CurrenciesConverterImpl.class.getName());
    
    /**
     * The exchange rates source.
     */
    ExchangeRatesSource exchangeRatesSource;
    
    /**
     * @inheritDoc
     */
    @Override
    public void setExchangeRatesSource(ExchangeRatesSource exchangeRatesSource)
                {
        this.exchangeRatesSource = exchangeRatesSource;
    }
    
    /**
     * The entry point method.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new
                                                ClassPathXmlApplicationContext
                                                ("applicationContext.xml");
        applicationContext.getBean(CurrenciesConverterImpl.class).run(args);
    }
    
    /**
     * The currency conversion method.
     * 
     * @param sourceCurrency The source currency.
     * @param targetCurrency The target currency.
     * @param amount The amount to be converted.
     * 
     * @return The amount of target currency possible to get for the source one.
     * 
     * @throws com.gmail.zietkowski.filip.exchangeratessource.exceptions
     * .UnknownCurrencyException The exception thrown when there's no data
     * about the chosen currency exchange rates.
     */
    public BigDecimal convertCurrency(String sourceCurrency,
                                      String targetCurrency,
                                      BigDecimal amount)
                      throws UnknownCurrencyException {
        BigDecimal exchangeRate;
        
        exchangeRate = exchangeRatesSource.getExchangeRate(sourceCurrency,
                                                           targetCurrency);
        return amount.multiply(exchangeRate);
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public void run(String[] args) {
        String sourceCurrency;
        String targetCurrency;
        
        BigDecimal amount;
        
        System.out.println("\n#### Welcome to a currency converter app. ####\n"
                           + "\nThis app handles the following conversions:\n"
                           + exchangeRatesSource.getAvailableExchangeRates()
                           + "\nPlease, type the source currency:");
        Scanner scanner = new Scanner(System.in);
        sourceCurrency = scanner.next();
        System.out.println("Please, type the target currency:");
        targetCurrency = scanner.next();
        System.out.println("Please, type the amount to convert:");
        while (!scanner.hasNextBigDecimal()) {
            scanner.next();
        }
        amount = scanner.nextBigDecimal();
        
        try {
            System.out.println("The given amount of source currency equals "
                               + convertCurrency(sourceCurrency, targetCurrency,
                               amount) + " in the target currency.");
        } catch (UnknownCurrencyException ex) {
            LOGGER.log(Level.SEVERE, "No data about the exchange rate of the"
                                     + " given currencies has been found.");
            // Maybe the above should just use System.err, but I guess logger
            // will be more useful if the application ever gets bigger.
            System.exit(ERROR_CODE_UNKNOWN_CURRENCY);
        }
    }
}
