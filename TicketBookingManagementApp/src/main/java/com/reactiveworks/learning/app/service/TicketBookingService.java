package com.reactiveworks.learning.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.app.dao.TicketBookingDao;
import com.reactiveworks.learning.app.entity.Ticket;

@Service
public class TicketBookingService {
 
 @Autowired
 TicketBookingDao ticketBookingDao;
 
 /**
  * This method is to create ticket object
  * @param ticket
  */
 public Ticket createTicket(Ticket ticket){
	 return ticketBookingDao.save(ticket);
 }//end of method
 
 /**
  * This method is to get the Ticket from ticketId
  * @param ticketId
  * @return
  */
 public Ticket getTicket(Integer ticketId){
	return ticketBookingDao.findOne(ticketId);
 }//end of method

/**
 * This method is to return all tickets
 * @return
 */
public List<Ticket> getAllTickets() {
	List<Ticket> ticketList=new ArrayList<>();
    ticketBookingDao.findAll().forEach(ticketList::add);
    return ticketList;
}//end of method 

/**
 * This method is update ticket
 * @param ticket
 * @param ticketId
 * @return
 */
public Ticket updateTicket(Ticket ticket,Integer ticketId) {
	String passnegerName=ticket.getPassengerName();
	Date bookingDate=ticket.getBookingDate();
	String sourceStation=ticket.getSourceStation();
	String destination=ticket.getDestination();
	String email=ticket.getEmail();
	ticket=new Ticket(ticketId, passnegerName, bookingDate, sourceStation, destination, email);
	ticketBookingDao.save(ticket);
	return ticket;
}//end of method

/**
 * This method is to delete the particular ticket from the DB
 * @param ticketId
 */
public void deleteTicket(Integer ticketId){
    ticketBookingDao.delete(ticketId);
}//end of method

/**
 * This method is to get all the  tickets  by passenger name 
 * @param passengerName
 * @return
 */
public List<Ticket> getTicketByName(String passengerName) {
	return ticketBookingDao.findByPassengerName(passengerName);
	
}

/**
 * This method is to get all the tickets by booking date
 * @param bookingDate
 * @return
 */
public List<Ticket> getTicketByBookingDate(Date bookingDate) {
	return ticketBookingDao.findByBookingDate(bookingDate);
}

/**
 * This method is to fiter out the tickets based on the passenger name
 * @param str
 * @return
 */
public List<Ticket>getTicketByPassengetNameLike(String str){
	return ticketBookingDao.findByPassengerNameLike(str);
}

/**
 * This method is to get Ticket by Destination
 * @param destination
 * @return
 */
public List<Ticket> findDestinationByDestination(String destination) {
	return ticketBookingDao.getTicketDetailsByDestination(destination);
}

/**
 * This method is to get all Tickets filtering using source station
 * @param sourceStation
 * @return
 */
public List<Ticket> findTicketBySource(String sourceStation){
	return ticketBookingDao.findBySourceStationInfo(sourceStation);
}
 
	
}
