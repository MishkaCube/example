package com.example.webhub.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    private String photo;
    private String name;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private Boolean enabled;
    private String adminKey;
}
