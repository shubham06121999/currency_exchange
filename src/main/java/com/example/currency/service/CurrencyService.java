package com.example.currency.service;

import com.example.currency.model.api.Rates;
import com.example.currency.model.api.RequestBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public  class CurrencyService {
    private final ObjectMapper mapper;

    public CurrencyService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Float GetConvertionValue(RequestBody body ) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.36:8080/v1/84c6ff4c5c6541ba88537c7651831d56")
                .get()
                .build();
        Response response = client.newCall(request).execute();
        JsonNode val = mapper.readTree(response.body().string());
        JsonNode obj = val.get("rates");
        String rateStr = obj.get(body.getConvertTo()).textValue();
        float rate = Float.parseFloat(rateStr);
        float value = Float.parseFloat(body.getValue());
        float result = value*rate;

        return result;
    }
}
