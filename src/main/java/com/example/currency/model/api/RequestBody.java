package com.example.currency.model.api;

import com.example.currency.model.enums.countryCode;
import com.example.currency.validation.EnumeratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Slf4j
public class RequestBody {

    @EnumeratedValue(enumClass = countryCode.class, message = "CountryCode should be only USD")
    public String CountryCode;
    public String Value;
    public String ConvertTo;
}
