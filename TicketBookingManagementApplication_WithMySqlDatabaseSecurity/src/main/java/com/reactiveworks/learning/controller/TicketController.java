package com.reactiveworks.learning.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveworks.learning.entity.Ticket;
import com.reactiveworks.learning.service.ITicketService;

/**
 * Controller Class for Ticket
 * And the base URL is starting from user 
 * URLs of this class can be accessed by ADMIN as well as USER level
 * based on Role Assigned
 * @author Md Noorshid
 */
@RestController
@RequestMapping(value="/user")
public class TicketController {
static Logger logger=Logger.getLogger(TicketController.class);
@Autowired	(required=true)
public ITicketService iTicketService;

@GetMapping(value="/ticket/{id}")
public ResponseEntity<Ticket> getTicketById(@PathVariable("id") String name){
	Ticket ticket=iTicketService.getTicketById(name);
	return new ResponseEntity<>(ticket,HttpStatus.OK);
}
@GetMapping(value="/getalltickets")
public ResponseEntity<List<Ticket>> getAllTickets(){
	List<Ticket> allTicketList=iTicketService.getAllTickets();
	if(allTicketList.size()>0){
	return new ResponseEntity<List<Ticket>>(allTicketList,HttpStatus.OK);
	}else{
		return new ResponseEntity<List<Ticket>>(allTicketList,HttpStatus.NO_CONTENT);
	}
}

@PostMapping(value="/createticket")
public ResponseEntity<String> createTicket(@RequestBody Ticket ticket){
	logger.info("inside createTicket method.....");
	Ticket ticketInDB=iTicketService.getTicketById(ticket.getPassengerName());
	if(ticketInDB!=null){
		logger.info("inside createTicket method ticketInDB!=null .....");
		return new ResponseEntity<String>("Ticket with the Id "+ticket.getPassengerName()+" already exist!!!",HttpStatus.CONFLICT);
	}else{
		logger.info("inside createTicket else part.....");
		iTicketService.createTicket(ticket);
	return new ResponseEntity<String>("Ticket has been create successfully",HttpStatus.CREATED);
	}
}

@PutMapping(value="/updateticket/{id}")
public ResponseEntity<Void> updateTopic(@RequestBody Ticket ticket,@PathVariable("id") String passengerName){
	iTicketService.updateTicket(ticket, passengerName);
	return new ResponseEntity<Void>(HttpStatus.OK);
	
}
@DeleteMapping(value="/deleteticket/{id}")
public ResponseEntity<Void> deleteTopic(@PathVariable("id") String name){
	iTicketService.deleteTicketById(name);
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}

}
