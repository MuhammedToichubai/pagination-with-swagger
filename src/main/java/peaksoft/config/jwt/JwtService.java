package peaksoft.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import peaksoft.dto.response.JwtTokenResponse;
import peaksoft.models.User;
import peaksoft.repo.UserRepo;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.security.jwt.secret_key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration}")
    private Long expiration;
    private final UserRepo userRepo;

    // create token // generate
    public  JwtTokenResponse  createToken(User user) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        String token = JWT.create()
                .withClaim("email", user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(zonedDateTime.toInstant())
                .withExpiresAt(zonedDateTime.plusSeconds(expiration).toInstant())
                .sign(getAlgorithm());

      return  new JwtTokenResponse(token, zonedDateTime, zonedDateTime.plusSeconds(expiration));
    }




    // verify token // validate
    public User verifyToken(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        String email = verify.getClaim("email").asString();
        return userRepo.findUserByEmailEqualsIgnoreCase(email);
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC512(secretKey);
    }
}
