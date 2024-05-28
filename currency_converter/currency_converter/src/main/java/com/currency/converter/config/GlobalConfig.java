package com.currency.converter.config;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

import org.yaml.snakeyaml.Yaml;

public class GlobalConfig {
    private static GlobalConfig instance;
    private String apiKey;

    private String yamlFileName = "application.yml";
    private GlobalConfig() {}

    public static GlobalConfig getInstance() {
        if (instance == null) {
            instance = new GlobalConfig();
        }
        return instance;
    }

    public String getAPIKey() {
        if(Objects.isNull(apiKey)){
            this.loadConfig();
        }
        return apiKey;
    }

    public void loadConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.yamlFileName);
        Map<String, Object> obj = yaml.load(inputStream);
        this.apiKey = (String) obj.get("api_key");
    }
}
