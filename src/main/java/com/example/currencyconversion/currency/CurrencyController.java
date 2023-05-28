/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/18/2023
 * Time: 6:10 PM
 * Project Name: CurrencyConversion
 */

package com.example.currencyconversion.currency;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyController {



    @Autowired
    CurrencyConversionService currencyConversionService;

    @GetMapping("/convertCurrency")
    public ResponseEntity<Map<String, Object>> convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount,
            @RequestParam String source
    ) {
        try {
            String result = currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source);
            Gson gson = new Gson();
            Map<String, Object> resultData = gson.fromJson(result, Map.class);
            return ResponseEntity.ok(resultData);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

//
//@Autowired
//CurrencyConversionService currencyConversionService;
//
//    @GetMapping("/")
//    public String convertCurrency (
//            @RequestParam String fromCurrency,
//            @RequestParam String toCurrency,
//            @RequestParam double amount,
//            @RequestParam String source,
//            Model model) {
//        try {
//            String result = currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source);
//            model.addAttribute("result", result);
//            return "index"; // this will return index.html
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "error"; // this will return error.html
//        }
//    }

}



//    @Autowired
//    CurrencyConversionService currencyConversionService;
//
//    @GetMapping("/convertCurrency")
//    public ResponseEntity<?> convertCurrency (
//            @RequestParam String fromCurrency,
//            @RequestParam String toCurrency,
//            @RequestParam double amount,
//            @RequestParam String source) {
//        try {
//            String result = currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount, source);
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }