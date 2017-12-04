package com.reactiveworks.learning.service;
/**
 * Service Class
 * @author Md Noorshid
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.dao.TicketDAO;
import com.reactiveworks.learning.entity.Ticket;

@Service
public class TicketService {
	@Autowired
	TicketDAO ticketDAO;
	/**
	 * Service to get a single ticket
	 * @param id
	 * @return
	 */
	public Ticket getTicket(Integer id){
		return ticketDAO.findOne(id);
	}
	
	/**
	 * Service to get all tickets
	 * @return
	 */
    public List<Ticket> getAllTickets(){
    	List<Ticket> allTickets=new ArrayList<Ticket>();
    	ticketDAO.findAll().forEach(allTickets::add);
    	return allTickets;
    }
    /**
     * Service to add a ticket
     * @param ticket
     * @return
     */
    public Ticket addTicket(Ticket ticket){
		return ticketDAO.save(ticket);
    }
    /**
     * Service to update a ticket
     * @return
     */
    public Ticket updateTicket(Integer id, Ticket ticket){
    	Ticket updatedTicket=new Ticket();
    	Integer reqId = id;
		String name = ticket.getName();
		String sourceStation = ticket.getSourceStation();
		Date bookingDate = ticket.getBookingDate();
		String destination = ticket.getDestination();
		String email = ticket.getEmail();
		updatedTicket.setId(reqId);
		updatedTicket.setName(name);
		updatedTicket.setSourceStation(sourceStation);
		updatedTicket.setBookingDate(bookingDate);
		updatedTicket.setDestination(destination);
		updatedTicket.setEmail(email);
		return ticketDAO.save(updatedTicket);
    }
    
    /**
     * Service to delete the ticket
     * @param id
     */
    public void deleteTicket(Integer id){
    	ticketDAO.delete(id);
    }
    
    /**
     * Service to find by email
     * @param email
     * @return
     */
    public Ticket findByEmail(String email){
    	return ticketDAO.findByEmail(email);
    }
    
    
    
}
