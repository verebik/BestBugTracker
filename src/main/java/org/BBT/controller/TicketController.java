package org.BBT.controller;

import org.BBT.data.entity.enums.Priority;
import org.BBT.data.entity.enums.Status;
import org.BBT.service.BugService;
import org.BBT.service.TicketManagementService;
import org.BBT.service.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Ez egy teszt!";
    }
    @PostMapping("saveUser")
    public UserDto saveUser(@RequestBody UserDto userDto) {
      return userService.save(userDto);
    }
    @PostMapping("saveBug")
    public BugDto saveBug(@RequestBody BugDto bugDto) {
        return bugService.createBug(bugDto);
    }
    @PostMapping("saveTicket")
    public TicketDto saveTicket(@RequestBody TicketDto ticketDto) {
        return ticketManagementService.addTicket(ticketDto);
    }
    @PostMapping("createTestUser")
    public UserDto createTestUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("teszt");
        userDto.setEmail("teszt@gmail.com");
        return  userService.save(userDto);
    }
    @PostMapping("createTestBug")
    public BugDto createTestBug() {
        BugDto bugDto = new BugDto();
        bugDto.setTitle("Teszt hiba");
        bugDto.setDescription("Teszt hiba leírása");
        return bugService.createBug(bugDto);
    }
    @PostMapping("createTestTicket")
    public TicketDto createTestTicket() {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setBug(bugService.getBugById(1L));
        ticketDto.setAssigneeUser(userService.findById(1L));
        ticketDto.setPriority(Priority.HIGH);
        ticketDto.setStatus(Status.OPEN);
        ticketDto.setCreatedAt(LocalDateTime.now());
        return ticketManagementService.addTicket(ticketDto);
    }


    @GetMapping("/allTickets")
    public List<TicketDto> getAllTickets() {
        return ticketManagementService.getAllTickets();
    }
}
