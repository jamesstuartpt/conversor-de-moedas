package com.currency.converter.menu.items;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import com.currency.converter.dtos.ConversionRatesDTO;
import com.currency.converter.entities.Currency;
import com.currency.converter.integration.ExchangerateAPI;
import com.currency.converter.menu.Item;

public class ConvertCurrencyBase extends Item{

    private Currency c1;
    private Currency c2;
    Scanner scanner = new Scanner(System.in);

    public ConvertCurrencyBase(int id, Currency c1, Currency c2){
        super(id, String.format("%s para %s", c1.getName(), c2.getName()));
        this.c1 = c1;
        this.c2 = c2;
    }

    public Currency getC1() {
        return c1;
    }

    public Currency getC2() {
        return c2;
    }

    @Override
    public void run() {
        ConversionRatesDTO conversionRatesDTO;
        Double conversionRateC2 = 1.1;
        try {
            conversionRatesDTO = ExchangerateAPI.get(c1.getCode());
            conversionRateC2 = conversionRatesDTO.conversionRates().get(c2.getCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    this.getUserInput(conversionRateC2);
    }

    private void errorMsg(){
        System.out.println("Digite um valor válido.");
    }

    private void getUserInput(Double conversionRate){
        Double input = -1.0;
        while(input <= 0.0){
            System.out.print(String.format("Digite o valor em %s para ser convertido em %s: ", c1.getCode(), c2.getCode()));
            try{
                input = Double.parseDouble(this.scanner.next());
                if(input <= 0) this.errorMsg();
            }catch(NumberFormatException e){
                this.errorMsg();
                continue;
            }
        }
        System.out.println(String.format("%.2f%s = %.2f%s\n", input, c1.getCode(), input * conversionRate, c2.getCode()));
    }
}
