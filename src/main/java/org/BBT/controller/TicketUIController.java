package org.BBT.controller;

import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller("ticketUIController")
@SessionScope
public class TicketUIController {

    @Autowired
    private TicketManagementService ticketManagementService;

    private String actionLabel;
    private TicketDto ticketDto;
    private List<TicketDto> ticketDtoList;

    @PostConstruct
    public void init() {
        refreshTicketList();
    }

    public void refreshTicketList() {
        ticketDtoList = ticketManagementService.getAllTickets();
    }

    public List<TicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public void setTicketDtoList(List<TicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }
}