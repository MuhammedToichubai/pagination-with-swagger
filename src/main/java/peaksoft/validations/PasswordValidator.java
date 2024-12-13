package peaksoft.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.models.User;

import java.util.List;


public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        return password.length() > 4;
//               password.matches(".*" + "[A-Z].*") && password.matches(".*[a-z].*")
//               && password.matches(".*\\d.*");
//
    }
}
