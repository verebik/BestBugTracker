package org.BBT.service;

import org.BBT.data.entity.UserEntity;
import org.BBT.service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findById(long id);
    List<UserDto> findAll();
    UserDto save(UserDto userDto);
    void deleteById(long id);

    UserEntity convertToEntity(UserDto assigneeUser);

    UserDto convertToDto(UserEntity assigneeUser);
}
