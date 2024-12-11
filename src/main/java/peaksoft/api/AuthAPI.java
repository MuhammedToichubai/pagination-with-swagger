package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.SingUpSingUpResponse;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthAPI {

    private final UserService userService;


    @PostMapping("/sign-up")
    public SingUpSingUpResponse signUp (@RequestBody RegisterRequest registerRequest){
        return userService.signUp(registerRequest);
    }

    @PostMapping("/sign-In")
    public SingUpSingUpResponse signIn (@RequestBody SignInRequest inRequest){
        return userService.signIn(inRequest);
    }







}
