package peaksoft.service;

import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.GetAllUserResponse;
import peaksoft.dto.response.SingUpSingUpResponse;
import peaksoft.dto.response.UserProfileResponse;

import java.security.Principal;
import java.util.List;

public interface UserService {

    SingUpSingUpResponse signUp(RegisterRequest registerRequest);

    SingUpSingUpResponse signIn(SignInRequest inRequest);

    UserProfileResponse getProfile(Principal principal);

    UserProfileResponse findById(Long userId);

    List<GetAllUserResponse> getAllUser();

}
