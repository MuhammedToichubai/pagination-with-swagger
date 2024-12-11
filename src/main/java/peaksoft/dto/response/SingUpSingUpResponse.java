package peaksoft.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import peaksoft.emuns.Role;

@Builder
public record SingUpSingUpResponse(
        JwtTokenResponse token,
        String email,
        Role role,
        String message,
        HttpStatus httpStatus

) {
}
