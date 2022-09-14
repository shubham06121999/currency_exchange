package com.example.currency.controller;


import com.example.currency.service.CurrencyService;
import com.example.currency.validation.groups.UpdateCurrencyGroup;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/v1")
public class CurrencyController {

    public final CurrencyService currencyService;


    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;

    }

    @GetMapping(path = "/convert")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Float getvalue(
            @Validated({UpdateCurrencyGroup.class})@RequestBody(required = true) com.example.currency.model.api.RequestBody body) throws IOException {
        return currencyService.GetConvertionValue(body);
    }



}
