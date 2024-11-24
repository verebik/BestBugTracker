package org.BBT.controller;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;
import org.BBT.service.BugService;
import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.BugDto;
import org.BBT.service.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketManagementService ticketManagementService;
    @Autowired
    BugService bugService;


    @GetMapping("/test")
    public String test() {return "Ez egy teszt!";}

    BugDto newBug = new BugDto(null,"Valami","Amerika");
    BugDto actualBug = bugService.createBug(newBug);

    TicketDto newTicket = new TicketDto();
    TicketDto addedTicket = ticketManagementService.addTicket(newTicket);

    /*TicketDto updatedTicket = new TicketDto(addedTicket.getId(), "Jane Smith", Priority.MEDIUM, Status.CLOSED, addedTicket.getCreatedAt(), LocalDateTime.now());
    ticketManagementService.updateTicket(updatedTicket);*/

    List<TicketDto> allTickets = ticketManagementService.getAllTickets();

}
