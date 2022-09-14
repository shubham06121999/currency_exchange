package com.example.currency.validation.impl;


import com.example.currency.validation.EnumeratedValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EnumeratedValueValidator implements ConstraintValidator<EnumeratedValue, Object> {

    List<Object> valueList = null;
    private boolean isOptional;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        boolean isValid = true;
        if (value != null && !ObjectUtils.isEmpty(value)) {
            if (this.isOptional) {
                isValid = valueList.contains(value.toString());
            } else {
                isValid = valueList.contains(value.toString().toUpperCase());
            }
        }
        return isValid;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void initialize(EnumeratedValue enumeratedValue) {
        valueList = new ArrayList<>();
        this.isOptional = enumeratedValue.caseSensitive();
        Class<? extends Enum<?>> enumClass = enumeratedValue.enumClass();
        Enum[] enumValArr = enumClass.getEnumConstants();

        try {
            for (@SuppressWarnings("rawtypes")
                    Enum enumVal : enumValArr) {
                Method method = BeanUtils.findMethod(enumClass, enumeratedValue.method());
                if (method != null && method.invoke(enumVal) != null) {
                    if (this.isOptional) {
                        valueList.add(method.invoke(enumVal).toString());
                    } else {
                        valueList.add(method.invoke(enumVal).toString().toUpperCase());
                    }
                } else {
                    log.debug("Enumerable object :[{}] There is no method in :[{}]", enumClass.getName(),
                            enumeratedValue.method());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
