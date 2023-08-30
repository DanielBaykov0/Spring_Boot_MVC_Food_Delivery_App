package baykov.daniel.fooddelivery.validation.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.INVALID_PERSON_NAME;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidPersonNameValidator.class)
public @interface ValidPersonName {

    String message() default INVALID_PERSON_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}