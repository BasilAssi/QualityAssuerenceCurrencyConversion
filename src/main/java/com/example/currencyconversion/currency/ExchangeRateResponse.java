/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/18/2023
 * Time: 6:47 PM
 * Project Name: CurrencyConversion
 */

package com.example.currencyconversion.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {
    private Map<String, Double> rates;
}