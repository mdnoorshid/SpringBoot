package com.reactiveworks.learning.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.dao.TicketControllerDao;
import com.reactiveworks.learning.entity.Ticket;

/**
 * This is the service class for the TicketBookingManagement Application
 * @author Md Noorshid
 *
 */
@Service
public class TicketBookingService {
	
	  @Autowired
	  TicketControllerDao ticketControllerDao;
  
	 /**
	  * Method to create ticket
	  * @param ticket
	  * @return
	  */
	public Ticket createTicket(Ticket ticket) {
		return ticketControllerDao.save(ticket);
	}
   
	/**
	 * Method to get ticket by id
	 * @param id
	 * @return
	 */
	public Ticket getTicketById(Integer id) {
		return ticketControllerDao.findOne(id);
	}

	/**
	 * Method to get All tickets
	 * @return
	 */
	public List<Ticket> getAllTickets() {
		List<Ticket> getAllTicketsList=new ArrayList<>();
		 ticketControllerDao.findAll().forEach(getAllTicketsList::add);
		 return getAllTicketsList;
	}

	/**
	 * Method to update the ticket by id
	 * @param ticket
	 * @param ticketId
	 * @return
	 */
	public Ticket updateTicketById(Ticket ticket, Integer ticketId) {
		String name=ticket.getName();
		String sourceStation=ticket.getSourceStation();
		String destination=ticket.getDestination();
		Date dateOfJourney=ticket.getDateOfJourney();
		String email=ticket.getEmail();
		ticket=new Ticket(name, sourceStation, destination, dateOfJourney, email);
		return ticketControllerDao.save(ticket);
	}

	/**
	 * Method to delete the ticket by id
	 * @param ticketId
	 */
	public void deleteTicketById(Integer ticketId) {
		ticketControllerDao.delete(ticketId);
	}



}
