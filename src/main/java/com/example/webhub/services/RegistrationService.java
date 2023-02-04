package com.example.webhub.services;

import com.example.webhub.mappers.UserMapper;
import com.example.webhub.repository.UsersRepository;
import com.example.webhub.user.RegistrationRequest;
import com.example.webhub.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UsersRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    @Value("${registration.key}")
    private String adminKey;

    public RegistrationService(UsersRepository repository, UserMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean signUp(RegistrationRequest request) {
        boolean isUserExists =
                repository.findByUsername(request.getUsername())
                        .isPresent();
        if (isUserExists) {
            throw new IllegalStateException("Nickname is already taken");
        }
        request.setRole(UserRole.ROLE_ADMIN);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setEnabled(true);

        if(request.getAdminKey().equals(adminKey)) {
            repository.save(mapper.toUser(request));
        } else {
            throw new BadCredentialsException("Неверный токен пользователя");
        }
        return true;
    }

}
