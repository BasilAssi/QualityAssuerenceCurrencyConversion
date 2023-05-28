/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/18/2023
 * Time: 6:15 PM
 * Project Name: CurrencyConversion
 */

package com.example.currencyconversion.currency;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CurrencyRepository extends JpaRepository<Currency, String> {



    @Query(value = "SELECT JSON_UNQUOTE(JSON_EXTRACT(conversion_rates, :targetCurrency)) FROM Currency WHERE from_code = :fromCurrency", nativeQuery = true)
    Double getConversionRate(@Param("fromCurrency") String fromCurrency, @Param("targetCurrency") String targetCurrency);

}
