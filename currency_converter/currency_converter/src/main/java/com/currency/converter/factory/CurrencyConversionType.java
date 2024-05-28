package com.currency.converter.factory;

import com.currency.converter.entities.Currency;
import com.currency.converter.menu.items.ConvertCurrencyBase;

public class CurrencyConversionType {

    public static final CurrencyConversionType USDToARS;
    public static final CurrencyConversionType ARSToUSD;
    public static final CurrencyConversionType USDToBRL;
    public static final CurrencyConversionType BRLToUSD;
    public static final CurrencyConversionType USDToCOP;
    public static final CurrencyConversionType COPToUSD;

    private static final Currency USD;
    private static final Currency ARS;
    private static final Currency BRL;
    private static final Currency COP;

    private final int id;
    private final Currency c1;
    private final Currency c2;

    static {
        USD = new Currency("Dólar", "USD");
        ARS = new Currency("Peso Argentino", "ARS");
        BRL = new Currency("Real Brasileiro", "BRL");
        COP = new Currency("Peso Comlombiano", "COP");

        USDToARS = register(
                Builder.of(1, USD, ARS));
        ARSToUSD = register(
                Builder.of(2, ARS, USD));
        USDToBRL = register(
                Builder.of(3, USD, BRL));
        BRLToUSD = register(
                Builder.of(4, BRL, USD));
        USDToCOP = register(
                Builder.of(5, USD, COP));
        COPToUSD = register(
                Builder.of(6, COP, USD));
    }

    public CurrencyConversionType(int id, Currency c1, Currency c2) {
        this.id = id;
        this.c1 = c1;
        this.c2 = c2;
    }

    public int getId() {
        return id;
    }

    public Currency getC1() {
        return c1;
    }

    public Currency getC2() {
        return c2;
    }

    public ConvertCurrencyBase toItemMenu() {
        return new ConvertCurrencyBase(this.id, this.c1, this.c2);
    }

    private static CurrencyConversionType register(Builder builder) {
        CurrencyConversionType currencyConversionType = builder.build();
        return currencyConversionType;
    }

    public static class Builder {
        private int id;
        private Currency c1;
        private Currency c2;

        public Builder(int id, Currency c1, Currency c2) {
            this.id = id;
            this.c1 = c1;
            this.c2 = c2;
        }

        public static Builder of(int id, Currency c1, Currency c2) {
            return new Builder(id, c1, c2);
        }

        public CurrencyConversionType build() {
            return new CurrencyConversionType(id, c1, c2);
        }
    }
}
