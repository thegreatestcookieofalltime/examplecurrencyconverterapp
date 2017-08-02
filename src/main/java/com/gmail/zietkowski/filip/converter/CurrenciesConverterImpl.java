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
    private static final Logger LOGGER = Logger.getLogger(
                                         CurrenciesConverterImpl.class
                                         .getName());
    
    /**
     * The exchange rates source.
     */
    ExchangeRatesSource exchangeRatesSource;
    
    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public void run(String[] args) {
        String sourceCurrency;
        String targetCurrency;
        
        BigDecimal amount;
        
        System.out.println("\n#### Welcome to Example Currencies Converter."
                           + " ####\n\nPlease, read the LICENSE.txt file before"
                           + " using it. You are allowed to use this"
                           + " application only if you accept its license"
                           + " (provided in that file).\n\nThis application"
                           + " handles the following conversions:\n"
                           + exchangeRatesSource.getAvailableExchangeRates()
                           + "\n\nPlease, be aware that the exchange rates "
                           + "used by this application are fake and so are the"
                           + " conversion results.\nIt's only an example"
                           + " application to demonstrate usage of the Spring "
                           + "Framework and therefore shouldn't be used for any"
                           + " financial calculations.\n\nPlease, type the"
                           + " source currency:");
        Scanner scanner = new Scanner(System.in);
        sourceCurrency = scanner.next();
        System.out.println("Please, type the amount to convert:");
        while (!scanner.hasNextBigDecimal()) {
            scanner.next();
        }
        amount = scanner.nextBigDecimal();
        System.out.println("Please, type the target currency:");
        targetCurrency = scanner.next();
        
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
