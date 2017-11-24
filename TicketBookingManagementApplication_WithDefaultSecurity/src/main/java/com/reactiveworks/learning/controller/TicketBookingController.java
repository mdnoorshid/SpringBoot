package com.reactiveworks.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveworks.learning.entity.Ticket;
import com.reactiveworks.learning.service.TicketBookingService;
/**
 * Controller Class for TicketBookingManagementApplication
 * @author Md Noorshid
 *
 */
@RestController
@RequestMapping(value="/api")
public class TicketBookingController {
     
	@Autowired
	TicketBookingService ticketBookingService;
	 
	/**
	 * Controller method to create the ticket on admin level
	 * @param ticket
	 * @return
	 */
	@PostMapping(value="/admin/create")
  	public Ticket createTicket(@RequestBody Ticket ticket){
  		return ticketBookingService.createTicket(ticket);
  	}
	
	/**
	 * Controller method to get the Ticket based on ticketId
	 * @param id
	 * @return
	 */
	@GetMapping(value="tickets/ticketid/{ticketid}")
	public Ticket getTicketById(@PathVariable("ticketid") Integer id){
		return ticketBookingService.getTicketById(id);
	}
	/**
	 * Controller method to get all tickets
	 * @return
	 */
	@GetMapping(value="tickets/alltickets")
	public List<Ticket> getAllTickets(){
		return ticketBookingService.getAllTickets();
		
	}
	/**
	 * Controller method to delete ticket by passenger id
	 * @param ticketId : passenger id
	 */
	@DeleteMapping(value="/admin/ticketid/{ticketid}")
	public void deleteTicketById(@PathVariable("ticketid") Integer ticketId){
		 ticketBookingService.deleteTicketById(ticketId);
	}
	
	/**
	 * Controller method to update ticket by passenger ticket id
	 * @param ticket
	 * @param ticketId
	 * @return
	 */
	@PutMapping(value="admin/update/{ticketid}")
	public Ticket updateTicketById(@RequestBody Ticket ticket, @PathVariable("ticketid") Integer ticketId){
		return ticketBookingService.updateTicketById(ticket,ticketId);
	}
	
	
	
}
