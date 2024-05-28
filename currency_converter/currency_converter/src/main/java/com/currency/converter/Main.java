package com.currency.converter;

import com.currency.converter.factory.CurrencyConversionType;
import com.currency.converter.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu
                .addItem(CurrencyConversionType.USDToARS.toItemMenu())
                .addItem(CurrencyConversionType.ARSToUSD.toItemMenu())
                .addItem(CurrencyConversionType.USDToBRL.toItemMenu())
                .addItem(CurrencyConversionType.BRLToUSD.toItemMenu())
                .addItem(CurrencyConversionType.USDToCOP.toItemMenu())
                .addItem(CurrencyConversionType.COPToUSD.toItemMenu());
                
        menu.build();
    }
}