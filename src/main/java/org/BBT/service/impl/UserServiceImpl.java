package org.BBT.service.impl;

import org.BBT.data.entity.UserEntity;
import org.BBT.data.repository.UserRepository;
import org.BBT.service.UserService;
import org.BBT.service.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        return mapper.map(dto, UserEntity.class);
    }

    @Override
    public UserDto convertToDto(UserEntity entity) {
        return mapper.map(entity, UserDto.class);
    }

    @Override
    public UserDto findById(long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return convertToDto(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        // DTO -> Entity
        UserEntity userEntity = convertToEntity(userDto);
        userEntity = userRepository.save(userEntity);
        return convertToDto(userEntity);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
