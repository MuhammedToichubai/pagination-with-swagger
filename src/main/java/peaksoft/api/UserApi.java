package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.response.GetAllUserResponse;
import peaksoft.dto.response.UserProfileResponse;
import peaksoft.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApi {

    private final UserService userService;

    @Secured("User")
    @GetMapping("/getProfile")
    public UserProfileResponse getProfile(Principal principal){
        return userService.getProfile(principal);
    }

    @Secured("ADMIN")
    @GetMapping("/getById/{userId}")
    public UserProfileResponse getUserById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @Secured("ADMIN")
    @GetMapping("/getAll")
    public List<GetAllUserResponse> getAllUser(){
        return userService.getAllUser();
    }


}
