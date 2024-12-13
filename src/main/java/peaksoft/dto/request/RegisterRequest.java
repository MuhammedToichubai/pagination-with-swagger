package peaksoft.dto.request;

import peaksoft.validations.EmailValidation;
import peaksoft.validations.PasswordValidation;

public record RegisterRequest(
        String name,
        String avatar,
        @EmailValidation
        String email,
        @PasswordValidation
        String  password
) {
}
