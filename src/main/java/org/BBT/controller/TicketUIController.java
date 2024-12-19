package org.BBT.controller;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;
import org.BBT.service.TicketManagementService;
import org.BBT.service.UserService;
import org.BBT.service.dto.TicketDto;
import org.BBT.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller("ticketUIController")
@SessionScope
public class TicketUIController {

    private static final Logger logger = Logger.getLogger(TicketUIController.class.getName());

    @Autowired
    private TicketManagementService ticketManagementService;

    @Autowired
    private UserService userService;

    private List<TicketDto> ticketDtoList;
    private List<TicketDto> filteredTicketDtoList;
    private List<UserDto> userList;
    private Priority[] priorities;
    private Status[] statuses;
    private String selectedStatus;
    private TicketDto selectedTicket;

    @PostConstruct
    public void init() {
        userList = userService.findAll();
        priorities = Priority.values();
        statuses = Status.values();
        //ticketDtoList.forEach(ticket -> ticket.setEditable(false));
        refreshTicketList();
    }

    public void refreshTicketList() {
        ticketDtoList = ticketManagementService.getAllTickets();
        filterTickets(selectedStatus);
    }

    public void filterTickets(String selectedStatus) {
        if (selectedStatus == null || selectedStatus.equals("All")) {
            filteredTicketDtoList = ticketDtoList;
        } else {
            filteredTicketDtoList = ticketDtoList.stream()
                    .filter(ticket -> ticket.getStatus().name().equals(selectedStatus))
                    .collect(Collectors.toList());
        }
        logger.info("Filtered tickets count for status " + selectedStatus + ": " + filteredTicketDtoList.size());
    }

    public void toggleEdit(TicketDto ticket) {
        ticket.setEditable(!ticket.getEditable());
    }

    public void saveTicketChanges(TicketDto ticket) {
        ticketManagementService.updateTicket(ticket);  // Ticket frissítése
        refreshTicketList();
        }


    // Getters and Setters

    public List<TicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public List<TicketDto> getFilteredTicketDtoList() {
        return filteredTicketDtoList;
    }

    public List<UserDto> getUserList() {
        return userList;
    }

    public Priority[] getPriorities() {
        return priorities;
    }

    public Status[] getStatuses() {
        return statuses;
    }

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(String selectedStatus) {
        this.selectedStatus = selectedStatus;
        filterTickets(selectedStatus);
    }

    public void deleteTicket(TicketDto ticket) {
        ticketManagementService.deleteTicket(ticket.getId());  // Ticket törlése
        refreshTicketList();  // A ticketek listájának frissítése
    }
    public void editTicket(TicketDto ticket) {
        this.selectedTicket = ticket;  // A kiválasztott ticket előhívása
    }

    public TicketDto getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(TicketDto selectedTicket) {
        this.selectedTicket = selectedTicket;
    }

}