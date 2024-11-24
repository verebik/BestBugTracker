package org.BBT.service.impl;

import org.BBT.data.entity.UserEntity;
import org.BBT.data.repository.UserRepository;
import org.BBT.service.UserService;
import org.BBT.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Lazy //temp solution
    @Autowired
    private TicketManagementServiceImpl ticketService;


    //TODO: Test function
    /*@Autowired
    private TicketRepository ticketRepository;

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());

        if (dto.getTickets() != null) {
            entity.setTickets(dto.getTickets().stream()
                    .map(ticketDto -> new TicketEntity(ticketDto))
                    .collect(Collectors.toList()));
        }

        return entity;
    }*/

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());

        if (dto.getTickets() != null) {
            entity.setTickets(dto.getTickets().stream()
                    .map(ticketDto -> ticketService.convertToEntity(ticketDto))
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    @Override
    public UserDto convertToDto(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail()
        );
    }

    @Override
    public UserDto findById(long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return convertToDto(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with id: " + id); // Hiba kezelése
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
        UserEntity userEntity = convertToEntity(userDto);
        userEntity = userRepository.save(userEntity);
        return convertToDto(userEntity);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}

