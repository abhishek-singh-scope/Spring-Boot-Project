package com.scope.project.exception.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentTypeValidator.class)
public @interface ValidatePayment {

    public String message() default "Invalid Payment Type: Must be Credit_Card or" +
            " Debit_Card or" +
            " Pay_on_delivery or" +
            " EMI";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
