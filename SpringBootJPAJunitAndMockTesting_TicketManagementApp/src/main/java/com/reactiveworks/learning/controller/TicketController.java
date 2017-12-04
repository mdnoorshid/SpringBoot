package com.reactiveworks.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveworks.learning.entity.Ticket;
import com.reactiveworks.learning.service.TicketService;

@RestController
@RequestMapping(value="/tickets")
public class TicketController {
    @Autowired
	TicketService ticketService;
    
    @CrossOrigin
    @GetMapping(value="/ticket/{id}",produces=MediaType.APPLICATION_JSON_VALUE)    
	public Ticket getTicketById(@PathVariable("id") Integer id){
		return ticketService.getTicket(id);
	}
	
    @CrossOrigin
    @GetMapping(value="/getAll",produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Ticket> getAllTickets(){
		return ticketService.getAllTickets();
    }
    
    @CrossOrigin
    @PostMapping(value="/createticket",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public Ticket createTicket(@RequestBody Ticket ticket){
    	return ticketService.addTicket(ticket);
    }
    
    @CrossOrigin
    @PutMapping(value="updateticket/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public Ticket updateTicket(@PathVariable("id") Integer ticketId,@RequestBody Ticket ticket){
		return ticketService.updateTicket(ticketId, ticket);
    	
    }
    
    @CrossOrigin
    @DeleteMapping(value="deleteticket/{id}")
    public void deleteTicket(@PathVariable("id") Integer id){
    	ticketService.deleteTicket(id);
    }
    
    @CrossOrigin
    @GetMapping(value="/getticketbyemail/{email}")
    public Ticket getByEmail(@PathVariable("email") String email){
    	return ticketService.findByEmail(email);
    }
    
}
