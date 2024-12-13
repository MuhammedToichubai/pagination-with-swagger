package peaksoft.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = {EmailValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {

    String message() default "{электронный почта камтысын @.com }";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
