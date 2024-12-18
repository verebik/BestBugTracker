package org.BBT.controller;

import org.BBT.service.BugService;
import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.BugDto;
import org.BBT.service.dto.TicketDto;
import org.BBT.service.dto.UserDto;
import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.util.List;

@Controller("bugController")
@ViewScoped
public class BugController {

    @Autowired
    private BugService bugManagementService;

    @Autowired
    private TicketManagementService ticketManagementService;

    private BugDto bugDto;
    private List<BugDto> bugDtoList;

    @PostConstruct
    public void init() {
        bugDto = new BugDto(); // Initialize bugDto
        refreshBugList();
    }

    public void refreshBugList() {
        bugDtoList = bugManagementService.getBugs();
    }

    public void addBugAndCreateTicket() {
        // Create a new Bug
        BugDto newBug = new BugDto();
        newBug.setTitle(bugDto.getTitle());
        newBug.setDescription(bugDto.getDescription());
        newBug = bugManagementService.createBug(newBug);

        // Create a new Ticket for the Bug
        TicketDto newTicket = new TicketDto();
        newTicket.setBug(newBug);
        newTicket.setAssigneeUser(null);
        newTicket.setPriority(null);
        newTicket.setStatus(Status.OPEN);
        newTicket.setCreatedAt(LocalDateTime.now());
        newTicket.setUpdatedAt(LocalDateTime.now());

        ticketManagementService.addTicket(newTicket);
    }

    public List<BugDto> getBugDtoList() {
        return bugDtoList;
    }

    public void setBugDtoList(List<BugDto> bugDtoList) {
        this.bugDtoList = bugDtoList;
    }

    public BugDto getBugDto() {
        return bugDto;
    }

    public void setBugDto(BugDto bugDto) {
        this.bugDto = bugDto;
    }

    public void onAssigneeChange(TicketDto ticket) {
        // Handle assignee change
        ticketManagementService.updateTicket(ticket);
    }

    public void onPriorityChange(TicketDto ticket) {
        // Handle priority change
        ticketManagementService.updateTicket(ticket);

    }

    public void onStatusChange(TicketDto ticket) {
        // Handle status change
        ticketManagementService.updateTicket(ticket);
    }
}