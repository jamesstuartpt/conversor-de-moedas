package com.currency.converter.integration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.currency.converter.config.GlobalConfig;
import com.currency.converter.dtos.ConversionRatesDTO;
import com.google.gson.Gson;

public class ExchangerateAPI {
    static final private String baseURL = "https://v6.exchangerate-api.com/v6";
    static final private String apiKey = GlobalConfig.getInstance().getAPIKey();
    static private HashMap<String, ConversionRatesDTO> cache = new HashMap<>();

    public static ConversionRatesDTO get(String currencyCode) throws MalformedURLException, IOException, InterruptedException{
        ConversionRatesDTO cached = loadFromCache(currencyCode);
        if(cached != null){
            System.out.println("Load from cache.");
            return cached;
        }
        String fullURL = baseURL + "/" + apiKey + "/latest/" + currencyCode;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullURL))
                .build();

        HttpResponse<String> response;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        ConversionRatesDTO dto = gson.fromJson(response.body(), ConversionRatesDTO.class);
        storeInCache(currencyCode, dto);
        return dto;
    }

    private static void storeInCache(String currencyCode, ConversionRatesDTO conversionRatesDTO){
        cache.put(currencyCode, conversionRatesDTO);
    }

    private static ConversionRatesDTO loadFromCache(String currencyCode){
        return cache.get(currencyCode);
    }
}
