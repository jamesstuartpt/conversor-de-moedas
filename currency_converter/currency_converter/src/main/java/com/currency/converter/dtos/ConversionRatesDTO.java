package com.currency.converter.dtos;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record ConversionRatesDTO(@SerializedName("conversion_rates") Map<String, Double> conversionRates) {}