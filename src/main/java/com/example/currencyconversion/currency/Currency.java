/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/18/2023
 * Time: 6:04 PM
 * Project Name: CurrencyConversion
 */

package com.example.currencyconversion.currency;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "from_currency") // changing 'from' to 'from_currency'
    private String fromCurrency; // This would be the base_code like 'USD'
    @ElementCollection
    private Map<String, Double> conversion_rates; // This would hold the conversion rates for different currencies



}