package com.reactiveworks.learning.app.controller;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * This is the controller class
 */
import org.springframework.web.bind.annotation.RestController;

import com.reactiveworks.learning.app.entity.Ticket;
import com.reactiveworks.learning.app.service.TicketBookingService;

@RestController
@RequestMapping("/api/tickets")
public class TicketBookingController {
	static Logger logger=Logger.getLogger(TicketBookingController.class);
	public static final String PATH="error";

	@Autowired
	TicketBookingService ticketBookingService;
    
	/**
	 * This method is to create the ticket
	 * @param ticket : Ticket object need to be create
	 * @return Ticket
	 */
	@PostMapping(value="/create")
	public Ticket creatTicket(@RequestBody Ticket ticket){
		return ticketBookingService.createTicket(ticket);
	}
	
	/**
	 * Accessing this URL to get the particular ticket
	 * @param ticketId
	 * @return
	 */
	@GetMapping(value="/ticket/{ticketId}")
	public Ticket getTicketById(@PathVariable("ticketId") Integer ticketId){
		return ticketBookingService.getTicket(ticketId);
	}
	
	/**
	 * Accessing this URL to get all tickets
	 * @return
	 */
	@GetMapping(value="/ticket/alltickets")
	public List<Ticket> getAllTickets(){
		return ticketBookingService.getAllTickets();
	}
	
	/**
	 * Accessing this URL to update the particular Ticket
	 * @param ticket
	 * @param ticketId
	 * @return
	 */
	@PutMapping(value="/ticket/{ticketId}")
	public Ticket updateTicket(@RequestBody Ticket ticket,@PathVariable("ticketId") Integer ticketId){
        return ticketBookingService.updateTicket(ticket,ticketId);		
	}
	
	/**
	 * Accessing this URL to delete the particular ticket
	 * @param ticketId
	 */
	@DeleteMapping(value="/ticket/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId") Integer ticketId){
		ticketBookingService.deleteTicket(ticketId);
	}
	
	/**
	 * Accesing this URL will provide all tickets matching with the passenger name
	 * @param passengerName
	 * @return
	 */
	@GetMapping(value="/ticket/alltickets/{passenger}")
	public List<Ticket> getTicketByPassengerName(@PathVariable("passenger") String passengerName){
		System.out.println(".inside getTicketByPassengerName controller.........");
		return ticketBookingService.getTicketByName(passengerName);
	}
	
	/**
	 * Accessing this URL provide all tickets matching with the booking date
	 * @param bookingDate
	 * @return
	 */
	@GetMapping(value="/ticket/alltickets/bookingdate?date={bookingDate}")
	public List<Ticket> getTicketByBookingDate(@RequestParam(value="date",required=true) @PathVariable("bookingDate") Date bookingDate){
		System.out.println(".inside getTicketByBookingDate controller.........");
		return ticketBookingService.getTicketByBookingDate(bookingDate);
	}
	
	/**
	 * Accessing this URL provide all tickets based on filtering using passenger name
	 * @param str
	 * @return
	 */
	@GetMapping(value="/ticket/alltickets/like/{passStr}")
	public List<Ticket> getTicketLikePassengerName(@PathVariable("passStr") String passStr){
		logger.debug(".inside getTicketLikePassengerName controller.........");
		return ticketBookingService.getTicketByPassengetNameLike(passStr);
	}
	
	/**
	 * Accessing this URL provide all tickets based on filtering using destination name
	 * @param str
	 * @return
	 */
	@GetMapping(value="/ticket/alltickets/destination/{destination}")
	public List<Ticket> getTicketByDestinationName(@PathVariable("destination") String destination){
		return ticketBookingService.findDestinationByDestination(destination);
	}
	
	/**
	 * Accessing this URL let take all tickets filtering using the source station
	 * @param sourceStation
	 * @return
	 */
	@GetMapping(value="/ticket/alltickets/source/{source}")
	public List<Ticket> getTicketsBySourceStation(@PathVariable("source") String sourceStation){
		return ticketBookingService.findTicketBySource(sourceStation);
	}
	
}
