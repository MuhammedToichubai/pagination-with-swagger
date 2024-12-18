package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.*;
import peaksoft.emuns.Role;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotfoundException;
import peaksoft.exceptions.response.ExceptionResponse;
import peaksoft.models.User;
import peaksoft.repo.UserRepo;
import peaksoft.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public SingUpSingUpResponse signUp(RegisterRequest registerRequest) {
        if (userRepo.existsByEmail(registerRequest.email())) {
            throw new BadRequestException("Email already exists");
        }

        User saveUser = userRepo.save(
                User.
                        builder()
                        .name(registerRequest.name())
                        .avatar(registerRequest.avatar())
                        .email(registerRequest.email())
                        .password(passwordEncoder.encode(registerRequest.password()))
                        .role(Role.USER)
                        .build());

        return SingUpSingUpResponse
                .builder()
                .token(jwtService.createToken(saveUser))
                .email(saveUser.getEmail())
                .role(saveUser.getRole())
                .httpStatus(HttpStatus.OK)
                .message("Successfully ")
                .build();
    }

    @Override
    public SingUpSingUpResponse signIn(SignInRequest inRequest) {
        User user = userRepo.getUserByEmail(inRequest.email());
        if (!passwordEncoder.matches(inRequest.password(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        return SingUpSingUpResponse
                .builder()
                .token(jwtService.createToken(user))
                .email(user.getEmail())
                .role(user.getRole())
                .httpStatus(HttpStatus.OK)
                .message("Successfully ")
                .build();
    }

    @Override
    public UserProfileResponse getProfile(Principal principal) {
        String email = principal.getName();
        User user = userRepo.getUserByEmail(email);
        return UserProfileResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public UserProfileResponse findById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new BadRequestException("not found user id " + userId));
        return UserProfileResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<GetAllUserResponse> getAllUser() {
        List<User> allUsers = userRepo.findAll();

        List<GetAllUserResponse> responseList = new ArrayList<>();

        for (User user : allUsers) {
            List<PostByUserResponse> posts = user.getPosts().stream()
                    .map(post -> new PostByUserResponse(
                            post.getId(),
                            post.getDescription(),
                            post.getImages(),
                            post.getCratedData(),
                            post.getUpdatedData()
                    ))
                    .toList();
            GetAllUserResponse response = new GetAllUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getAvatar(),
                    user.getRole(),
                    posts
            );

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public PaginationResponse<UserResponse> getAllWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
        Page<User> usersPage = userRepo.findAll(pageable);

        List<UserResponse> responseList = new ArrayList<>();

        usersPage.getContent().forEach(user ->
                responseList.add(new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole())));

        PaginationResponse<UserResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setPageNumber(usersPage.getNumber() + 1 );
        paginationResponse.setPageSize(usersPage.getSize());
        paginationResponse.setNumberOfPages(usersPage.getTotalPages());
        paginationResponse.setNumberOfElements(usersPage.getTotalElements());
        paginationResponse.setObjects(responseList);
        return paginationResponse;
    }
}