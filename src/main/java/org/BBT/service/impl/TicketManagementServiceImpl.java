package org.BBT.service.impl;

import org.BBT.data.entity.BugEntity;
import org.BBT.data.entity.TicketEntity;
import org.BBT.data.entity.UserEntity;
import org.BBT.data.repository.TicketRepository;
import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.TicketDto;
import org.BBT.service.dto.BugDto;
import org.BBT.service.dto.UserDto;
import org.BBT.service.BugService;
import org.BBT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketManagementServiceImpl implements TicketManagementService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    BugService bugService;

    @Autowired
    UserService userService;

    // TicketDto konvertálás TicketEntity-be
    public TicketEntity convertToEntity(TicketDto dto) {
        TicketEntity entity = new TicketEntity();
        entity.setId(dto.getId());

        // AssigneeUser konvertálása UserDto -> UserEntity
        if (dto.getAssigneeUser() != null) {
            UserEntity userEntity = userService.convertToEntity(dto.getAssigneeUser());
            entity.setAssigneeUser(userEntity);
        }

        // Bug konvertálása BugDto -> BugEntity
        if (dto.getBug() != null) {
            BugEntity bugEntity = bugService.convertToEntity(dto.getBug());
            entity.setBug(bugEntity);
        }

        entity.setPriority(dto.getPriority());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    // TicketEntity konvertálás TicketDto-ra
    public TicketDto convertToDto(TicketEntity entity) {
        BugDto bugDto = bugService.convertToDto(entity.getBug());
        UserDto userDto = userService.convertToDto(entity.getAssigneeUser());

        return new TicketDto(
                entity.getId(),
                bugDto,
                userDto,
                entity.getPriority(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()

        );
    }

    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        TicketEntity ticketEntity = convertToEntity(ticketDto);
        ticketEntity = ticketRepository.save(ticketEntity);
        return convertToDto(ticketEntity);
    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto) {
        Optional<TicketEntity> optionalTicket = ticketRepository.findById(ticketDto.getId());
        if (optionalTicket.isPresent()) {
            TicketEntity ticketEntity = optionalTicket.get();
            ticketEntity.setAssigneeUser(convertToEntity(ticketDto).getAssigneeUser());
            ticketEntity.setPriority(ticketDto.getPriority());
            ticketEntity.setStatus(ticketDto.getStatus());
            ticketEntity.setUpdatedAt(ticketDto.getUpdatedAt());

            ticketEntity = ticketRepository.save(ticketEntity);
            return convertToDto(ticketEntity);
        } else {
            return null;
        }
    }

    @Override
    public TicketDto getTicket(String ticketId) {
        return null; //TODO: FIND BY NAME NOT ID
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        return ticketEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTicket(String ticketId) { //Not string, need to rework
        try {
            Long id = Long.parseLong(ticketId);
            ticketRepository.deleteById(id);
        } catch (NumberFormatException e) {
            //TODO: catch error
        }
    }
}
