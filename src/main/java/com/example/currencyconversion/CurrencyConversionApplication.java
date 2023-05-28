package com.example.currencyconversion;

import com.example.currencyconversion.currency.Currency;
import com.example.currencyconversion.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CurrencyConversionApplication implements CommandLineRunner {


    @Autowired
    private CurrencyRepository currencyRepository;
    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(String... args) {
      //  Currency currency = new Currency();
//        currency.set_id(12321313);
//        currency.setFrom("SAL");


        // set other fields...

       // currencyRepository.save(currency);
       // System.out.println(currencyRepository.findAll());


        System.out.println("Saved currency to MongoDB.");
    }
}
