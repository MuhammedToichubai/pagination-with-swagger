package peaksoft.dto.request;

import jakarta.validation.constraints.*;
import peaksoft.validations.EmailValidation;
import peaksoft.validations.PasswordValidation;

public record RegisterRequest(
        @NotBlank(message = "Name cannot be empty [Аты бош болбошу керек] ")
        @Size(min = 3, max = 20, message = "Аты 3 тамгадан коп жана 20 дан аз болсун!")
        String name,

        String avatar,

       // @EmailValidation
        @Email(
                regexp =  "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$",
                message = "Invalid email format. Only end with .com")
        String email,

//        @PasswordValidation
        @Pattern(regexp = "^[0-9]{4,}$"
        )
        String  password

) {
}
