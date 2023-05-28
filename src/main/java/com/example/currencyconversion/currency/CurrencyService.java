/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/18/2023
 * Time: 6:17 PM
 * Project Name: CurrencyConversion
 */

package com.example.currencyconversion.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}