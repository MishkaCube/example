package com.example.webhub.controllers;

import com.example.webhub.configuration.JwtUtil;
import com.example.webhub.services.RegistrationService;
import com.example.webhub.user.AuthDto;
import com.example.webhub.user.InvalidTokenException;
import com.example.webhub.user.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@RequestMapping("api/v1/registration")
@CrossOrigin("https://admin.devcloudy.ru")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ApiIgnore
    public String register(@RequestBody RegistrationRequest request) {
        if(registrationService.signUp(request)) {
            return "Администратор успешно зарегистрирован";
        };
        return "Администратор не зарегистрирован, ошибка ввода данных или сервера.";
    }

    @GetMapping("verifyToken")
    public Map<String, String> verifyToken(@RequestParam String token) {
        return Map.of("username of this token", jwtUtil.validateTokenAndRetrieveClaim(token));
    }

    @PostMapping(value = "/login")
    public Map<String, String> auth(@RequestBody AuthDto request) {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials");
        }

        String jwt = jwtUtil.generateToken(request.getUsername());
        return Map.of("accessToken", jwt);
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/profile")
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
