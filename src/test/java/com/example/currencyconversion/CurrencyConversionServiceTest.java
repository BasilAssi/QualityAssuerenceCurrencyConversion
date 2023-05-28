/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/19/2023
 * Time: 9:22 PM
 * Project Name: CurrencyConversion
 */

package com.example.currencyconversion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.currencyconversion.currency.CurrencyConversionService;
import com.example.currencyconversion.currency.CurrencyRepository;
import com.example.currencyconversion.currency.ExchangeRateResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.function.Executable;

import java.util.Map;
import com.google.gson.Gson;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyConversionServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CurrencyRepository currencyRepository;

     CurrencyConversionService currencyConversionService;

    // Revised test cases for API and DB sources
    // Revised test cases for API and DB sources
    @Test
    public void testConvertCurrency_API() throws Exception {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 100;
        String source = "api";
        double exchangeRate = 0.85;

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setRates(Map.of(toCurrency, exchangeRate));

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class)))
                .thenReturn(exchangeRateResponse);

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        String result = currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source);

        Map<String, Double> resultMap = new Gson().fromJson(result, Map.class);
        assertEquals(amount * exchangeRate, resultMap.get("finalResult"));
        assertEquals(exchangeRate, resultMap.get("rate"));
    }


    // New test cases for empty toCurrency, empty fromCurrency, and non-positive amount
    @Test
    public void testConvertCurrency_EmptyToCurrency() {
        String fromCurrency = "USD";
        String toCurrency = ""; // Empty toCurrency
        double amount = 100;
        String source = "api";

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(Exception.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("The To Currency Can't be Empty.", exception.getMessage());
    }

    @Test
    public void testConvertCurrency_EmptyFromCurrency() {
        String fromCurrency = ""; // Empty fromCurrency
        String toCurrency = "EUR";
        double amount = 100;
        String source = "api";

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(Exception.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("The From Currency Can't be Empty.", exception.getMessage());
    }

    @Test
    public void testConvertCurrency_NonPositiveAmount() {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 0; // Non-positive amount
        String source = "api";

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(Exception.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("Amount should be Greater then zero", exception.getMessage());
    }


    @Test
    public void testConvertCurrency_DB() throws Exception {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 100;
        String source = "db";
        double exchangeRate = 0.85;

        when(currencyRepository.getConversionRate(fromCurrency, "$." + toCurrency))
                .thenReturn(exchangeRate);

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        String result = currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source);

        Map<String, Double> resultMap = new Gson().fromJson(result, Map.class);
        assertEquals(amount * exchangeRate, resultMap.get("finalResult"));
        assertEquals(exchangeRate, resultMap.get("rate"));
    }



    // Test for empty source
    @Test
    public void testConvertCurrency_EmptySource() {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 100;
        String source = ""; // Empty source

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("Invalid source", exception.getMessage());
    }

    @Test
    public void testConvertCurrency_InvalidCurrencyCode_API() {
        // Initialize new variables for this test case
        String testFromCurrency = "USD";
        String testToCurrency = "INVALID"; // Invalid currency code
        double testAmount = 100;
        String testSource = "api";

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setRates(Map.of()); // Empty rates

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class)))
                .thenReturn(exchangeRateResponse);

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);

        // Create new Executable for the assertThrows
        Executable executable = () -> {
            currencyConversionService.convertCurrency(testFromCurrency, testToCurrency, testAmount, testSource);
        };

        Exception exception = assertThrows(IllegalArgumentException.class, executable);

        assertEquals("Invalid currency code", exception.getMessage());
    }


    @Test
    public void testConvertCurrency_InvalidCurrencyCode_API1() {
        // Initialize new variables for this test case
        String testFromCurrency = "USD";
        String testToCurrency = "INVALID"; // Invalid currency code
        double testAmount = 100;
        String testSource = "api";

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setRates(Map.of()); // Empty rates

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class)))
                .thenReturn(exchangeRateResponse);

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                currencyConversionService.convertCurrency(testFromCurrency, testToCurrency, testAmount, testSource));

        assertEquals("Invalid currency code", exception.getMessage());
    }

    @Test
    public void testConvertCurrency_EmptySource1() {
        String testFromCurrency = "USD";
        String testToCurrency = "EUR";
        double testAmount = 100;
        String testSource = ""; // Empty Source

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);

        Executable executable = () -> {
            currencyConversionService.convertCurrency(testFromCurrency, testToCurrency, testAmount, testSource);
        };

        Exception exception = assertThrows(IllegalArgumentException.class, executable);

        assertEquals("Invalid source", exception.getMessage());
    }






    // Test for negative amount
    @Test
    public void testConvertCurrency_NegativeAmount() {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = -100; // Negative amount
        String source = "api";

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(Exception.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("Amount should be Greater then zero", exception.getMessage());
    }



    // Test for invalid currency code with API
    @Test
    public void testConvertCurrency_InvalidCurrencyCode_API2() {
        String fromCurrency = "USD";
        String toCurrency = "INVALID"; // Invalid currency code
        double amount = 100;
        String source = "api";

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setRates(Map.of()); // Empty rates

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class)))
                .thenReturn(exchangeRateResponse);

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("Invalid currency code", exception.getMessage());
    }

    // Test for invalid currency code with DB
    @Test
    public void testConvertCurrency_InvalidCurrencyCode_DB() {
        String fromCurrency = "USD";
        String toCurrency = "INVALID"; // Invalid currency code
        double amount = 100;
        String source = "db";

        when(currencyRepository.getConversionRate(fromCurrency, "$." + toCurrency))
                .thenReturn(null);

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(Exception.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("There was an error while retrieving the exchange rate from the database.", exception.getMessage());
    }

    // Test for invalid source
    @Test
    public void testConvertCurrency_InvalidSource() {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 100;
        String source = "invalid"; // Invalid source

        currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

        assertEquals("Invalid source", exception.getMessage());
    }





        @Nested
        class ConvertCurrencyTests {
            @Test
            public void testBlankFromCurrency() {
                String fromCurrency = "  "; // Blank fromCurrency
                String toCurrency = "EUR";
                double amount = 100;
                String source = "api";

                currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
                Exception exception = assertThrows(Exception.class, () ->
                        currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

                assertEquals("The From Currency Can't be Empty.", exception.getMessage());
            }

            @Test
            public void testBlankToCurrency() {
                String fromCurrency = "USD";
                String toCurrency = "  "; // Blank toCurrency
                double amount = 100;
                String source = "api";

                currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
                Exception exception = assertThrows(Exception.class, () ->
                        currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

                assertEquals("The To Currency Can't be Empty.", exception.getMessage());
            }

            @Test
            public void testInvalidSource() {
                String fromCurrency = "USD";
                String toCurrency = "EUR";
                double amount = 100;
                String source = "  "; // Blank source

                currencyConversionService = new CurrencyConversionService(restTemplate, currencyRepository);
                Exception exception = assertThrows(IllegalArgumentException.class, () ->
                        currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source));

                assertEquals("Invalid source", exception.getMessage());
            }
        }
    }


