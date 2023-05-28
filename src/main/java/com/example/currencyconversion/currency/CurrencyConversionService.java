package com.example.currencyconversion.currency;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {


    private RestTemplate restTemplate;
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyConversionService(RestTemplate restTemplate, CurrencyRepository currencyRepository) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
    }


    public String convertCurrency(String fromCurrency, String toCurrency, double amount, String source) throws Exception {

        if (toCurrency.isEmpty() || toCurrency.isBlank() || toCurrency.equals("")) {
            throw new Exception("The To Currency Can't be Empty.");
        } else {
            if (fromCurrency.isEmpty() || fromCurrency.isBlank() || fromCurrency.equals("")) {
                throw new Exception("The From Currency Can't be Empty.");
            } else {

                if (amount > 0) {

                    double exchangeRate = Double.MIN_NORMAL;

                    switch (source.toLowerCase()) {
                        case "api":
                            String apiUrl = String.format("https://api.exchangerate-api.com/v4/latest/%s", fromCurrency);
                            ExchangeRateResponse exchangeRateResponse = restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);

                            if (exchangeRateResponse != null) {
                                Double rate = exchangeRateResponse.getRates().get(toCurrency);
                                System.out.println("rate from api" + rate);
                                if (rate != null) {
                                    exchangeRate = rate;
                                } else {
                                    throw new IllegalArgumentException("Invalid currency code");
                                }
                            } else {
                                throw new RuntimeException("Failed to get data from API");
                            }
                            break;
                        case "db":
                            Double rate = currencyRepository.getConversionRate(fromCurrency, "$." + toCurrency);
                            System.out.println("rate from db" + rate);
                            if (rate != null) {
                                exchangeRate = rate;
                            } else {
                                throw new Exception("There was an error while retrieving the exchange rate from the database.");
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid source");
                    }
                    double result = amount * exchangeRate;
                    String formattedResult = String.format("%.6f", result);
                    double finalResult = Double.parseDouble(formattedResult);

                    // Here we are creating a map to hold the finalResult and exchangeRate
                    Map<String, Double> resultData = new HashMap<>();
                    resultData.put("finalResult", finalResult);
                    resultData.put("rate", exchangeRate);

                    // Here we are converting the Map to a JSON string using Gson library
                    Gson gson = new Gson();
                    String json = gson.toJson(resultData);

                    return json;
                } else {
                    throw new Exception("Amount should be Greater then zero ");
                }
            }
        }
    }
}