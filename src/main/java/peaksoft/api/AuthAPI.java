package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.SingUpSingUpResponse;
import peaksoft.exceptions.BadRequestException;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthAPI {

    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp (@Valid @RequestBody RegisterRequest registerRequest){
        try {
           return ResponseEntity.ok(userService.signUp(registerRequest));

        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-In")
    public SingUpSingUpResponse signIn (@RequestBody SignInRequest inRequest){
        return userService.signIn(inRequest);
    }







}
