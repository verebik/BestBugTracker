package org.BBT.service;

import org.BBT.service.dto.TicketDto;

import java.util.List;

public interface TicketManagementService {
    TicketDto addTicket(TicketDto ticket);
    TicketDto updateTicket(TicketDto ticket);
    TicketDto getTicket(String ticketId);
    List<TicketDto> getAllTickets();
    void deleteTicket(String ticketId);
}
