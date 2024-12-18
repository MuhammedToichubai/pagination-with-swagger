package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import peaksoft.emuns.Role;
import peaksoft.models.User;
import peaksoft.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class DefaultDataService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct // inint method
    public void  saveDefaultAdmin(){
        String email = "admin@gmail.com";
        boolean exists = userRepo.existsByEmail(email);
        if (!exists) {
            userRepo.save(
                    User.builder()
                            .name("Admin")
                            .email(email)
                            .role(Role.ADMIN)
                            .avatar("https://skdjfhkjsh234kjsldkfjs")
                            .password(passwordEncoder.encode("admin"))
                            .build()
            );
        }
    }





}
