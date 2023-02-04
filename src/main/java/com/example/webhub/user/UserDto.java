package com.example.webhub.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;

    private String email;

    private Boolean enabled = false;

    private String password;

    private String username;
}
