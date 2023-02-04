package com.example.webhub.mappers;

import com.example.webhub.entity.User;
import com.example.webhub.user.RegistrationRequest;
import com.example.webhub.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User entity);
    User toUser(RegistrationRequest request);

    UserDto userDtoToRegistrationRequest(RegistrationRequest request);
}
