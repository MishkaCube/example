package com.example.webhub.entity;

import com.example.webhub.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "role", nullable = false)
    private UserRole role;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 255)
    @Column(name = "username")
    private String username;


}