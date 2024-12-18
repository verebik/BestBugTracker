package org.BBT.service.impl;

import org.BBT.data.entity.TicketEntity;
import org.BBT.data.repository.TicketRepository;
import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.TicketDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketManagementServiceImpl implements TicketManagementService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper modelMapper;

    // TicketDto -> TicketEntity
    private TicketEntity convertToEntity(TicketDto dto) {
        return modelMapper.map(dto, TicketEntity.class);
    }

    // TicketEntity -> TicketDto
    private TicketDto convertToDto(TicketEntity entity) {
        return modelMapper.map(entity, TicketDto.class);
    }

    @Override
    @Transactional
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

            modelMapper.map(ticketDto, ticketEntity);
            ticketEntity = ticketRepository.save(ticketEntity);

            return convertToDto(ticketEntity);
        } else {
            return null;
        }
    }

    @Override
    public TicketDto getTicket(long ticketId) {
        try {
            Optional<TicketEntity> ticketEntity = ticketRepository.findById(ticketId);
            return ticketEntity.map(this::convertToDto).orElse(null);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong ticket id");
        }
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        return ticketEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTicket(long ticketId) {
        try {
            ticketRepository.deleteById(ticketId);
        } catch (NumberFormatException e) {
            // Hibakezelés: Érvénytelen azonosító
        }
    }
}
