package com.example.webhub.user;

import com.example.webhub.entity.User;
import com.example.webhub.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = repository.findByUsername(username);


        if (users.isEmpty()) {
            throw new UsernameNotFoundException("This user is not present");
        }


        return new com.example.webhub.user.UsersDetails(users.get());
    }
}
