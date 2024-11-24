package org.BBT.service.impl;

import org.BBT.data.entity.TicketEntity;
import org.BBT.data.repository.TicketRepository;
import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketManagementServiceImpl implements TicketManagementService {

    @Autowired
    TicketRepository ticketRepository;

    // TicketDto konvertálás TicketEntity-be
    private TicketEntity convertToEntity(TicketDto dto) {
        TicketEntity entity = new TicketEntity();
        entity.setAssigneeUser(dto.getAssigneeUser());
        entity.setPriority(dto.getPriority());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    // TicketEntity konvertálás TicketDto-ra
    private TicketDto convertToDto(TicketEntity entity) {
        return new TicketDto(
                entity.getId(),
                entity.getBug(),
                entity.getAssigneeUser(),
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
            ticketEntity.setAssigneeUser(ticketDto.getAssigneeUser());
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
        Optional<TicketEntity> ticketEntity = ticketRepository.findById(Long.parseLong(ticketId));
        return ticketEntity.map(this::convertToDto).orElse(null);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        return ticketEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTicket(String ticketId) {
        ticketRepository.deleteById(Long.parseLong(ticketId));
    }
}
