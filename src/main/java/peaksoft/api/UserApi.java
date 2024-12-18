package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.response.GetAllUserResponse;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.UserProfileResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApi {

    private final UserService userService;

    @Secured("USER")
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


    @GetMapping
    public PaginationResponse<UserResponse> getAllWithPagination(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize ){
        return userService.getAllWithPagination(pageNumber, pageSize);
    }



}
