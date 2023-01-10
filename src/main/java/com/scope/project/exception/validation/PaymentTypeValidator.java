package com.scope.project.exception.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PaymentTypeValidator implements ConstraintValidator<ValidatePayment,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> methods = Arrays.asList("Credit_Card",
                "Debit_Card",
                "Pay_on_delivery",
                "EMI");

        return methods.contains(s);
    }
}
