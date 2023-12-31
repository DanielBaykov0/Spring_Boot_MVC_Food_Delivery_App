package baykov.daniel.fooddelivery.validation.order;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static baykov.daniel.fooddelivery.constant.ErrorMessages.INVALID_DISCOUNT;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DiscountMatchValidator.class)
public @interface DiscountMatch {

    String message() default INVALID_DISCOUNT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
