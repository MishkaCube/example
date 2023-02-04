package com.example.webhub.services;

import com.example.webhub.entity.User;
import com.example.webhub.mappers.UserMapper;
import com.example.webhub.repository.UsersRepository;
import com.example.webhub.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository repository;
    private final UserMapper mapper;

    public List<UserDto> getAllUsers() {
        return repository.findAll().stream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public Optional<User> findUserByName(String username) {
        return repository.findByUsername(username).stream().findFirst();
    }
}
