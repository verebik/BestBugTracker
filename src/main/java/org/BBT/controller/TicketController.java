package org.BBT.controller;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;
import org.BBT.service.BugService;
import org.BBT.service.TicketManagementService;
import org.BBT.service.dto.BugDto;
import org.BBT.service.dto.TicketDto;
import org.BBT.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketManagementService ticketManagementService;

    @Autowired
    private BugService bugService;

    @GetMapping("/test")
    public String test() {
        return "Ez egy teszt!";
    }

    @PostMapping("/createTicket")
    public TicketDto createTicket() {
        // Létrehozás a metódusban
        UserDto newUser = new UserDto(null, "KisS Pista", "kispista@gmail.com");
        BugDto newBug = new BugDto(null, "Valami", "Amerika");

        // Bug létrehozása
        BugDto actualBug = bugService.createBug(newBug);

        // Ticket létrehozása
        TicketDto newTicket = new TicketDto(null, actualBug, newUser, Priority.HIGH, Status.OPEN, LocalDateTime.now(), null);

        // Ticket hozzáadása
        TicketDto addedTicket = ticketManagementService.addTicket(newTicket);

        return addedTicket;
    }

    @GetMapping("/allTickets")
    public List<TicketDto> getAllTickets() {
        return ticketManagementService.getAllTickets();
    }
}
