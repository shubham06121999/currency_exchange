package com.example.currency.validation;

import com.example.currency.validation.impl.EnumeratedValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumeratedValueValidator.class)
public @interface EnumeratedValue {

    Class<? extends Enum<?>> enumClass();

    String message() default "Enum Type Value Is Incorrect";

    String method() default "getValue";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean caseSensitive() default true;

}
