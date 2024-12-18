package org.BBT.controller;

import org.BBT.data.entity.enums.Status;
import org.BBT.service.TicketManagementService;
import org.BBT.service.UserService;
import org.BBT.service.dto.TicketDto;
import org.BBT.service.dto.UserDto;
import org.BBT.data.entity.enums.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller("ticketUIController")
@SessionScope
public class TicketUIController {

    /*TODO: FIX data modification problem , buggyDropdownd*/

    private static final Logger logger = Logger.getLogger(TicketUIController.class.getName());

    @Autowired
    private TicketManagementService ticketManagementService;

    @Autowired
    private UserService userService;

    private String actionLabel;
    private TicketDto ticketDto;
    private List<TicketDto> ticketDtoList;
    private List<TicketDto> filteredTicketDtoList;
    private List<UserDto> userList;
    private Priority[] priorities;
    private Status[] statuses;
    private Status selectedStatus;

    @PostConstruct
    public void init() {
        refreshTicketList();
        userList = userService.findAll();
        if (userList == null || userList.isEmpty()) {
            logger.severe("User list is empty or null!");
        } else {
            logger.info("User list populated with " + userList.size() + " users.");
        }
        priorities = Priority.values();
        statuses = Status.values();
        selectedStatus = null; // Default to show all tickets
    }

    public void refreshTicketList() {
        ticketDtoList = ticketManagementService.getAllTickets();
        userList = userService.findAll();
        filterTickets();
    }

    public void filterTickets() {
        if (selectedStatus == null) {
            filteredTicketDtoList = ticketDtoList;
        } else {
            filteredTicketDtoList = ticketDtoList.stream()
                    .filter(ticket -> ticket.getStatus() == selectedStatus)
                    .collect(Collectors.toList());
        }
    }

    public List<TicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public void setTicketDtoList(List<TicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }

    public List<TicketDto> getFilteredTicketDtoList() {
        return filteredTicketDtoList;
    }

    public void setFilteredTicketDtoList(List<TicketDto> filteredTicketDtoList) {
        this.filteredTicketDtoList = filteredTicketDtoList;
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

    public List<UserDto> getUserList() {
        return userList;
    }

    public Priority[] getPriorities() {
        return priorities;
    }

    public Status[] getStatuses() {
        return statuses;
    }
    public void modifyTicket(TicketDto ticket) {
        if (ticket != null) {
            ticketManagementService.updateTicket(ticket);
            refreshTicketList(); // Frissíti az aktuális lista megjelenítését
        }
    }
    public Status getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Status selectedStatus) {
        this.selectedStatus = selectedStatus;
    }


}