package com.reactiveworks.learning.dao.imp;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.reactiveworks.learning.dao.ITicketDAO;
import com.reactiveworks.learning.entity.Ticket;

/**
 * DAO LAYER
 * Implementation class for ITicketDAO and Overriding the methods.
 * Here database interaction is doing by EntityManager
 * @author Niyamat
 *
 */
@Component
@Transactional
@Repository
public class TicketDAOImpl implements ITicketDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Ticket> getAllTickets() {
		String HQL="From Ticket as t ORDER BY t.passengerName";
		return (List<Ticket>)entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public Ticket getTicketById(String name) {
		return entityManager.find(Ticket.class, name);
	}

	@Override
	public Ticket createTicket(Ticket ticket) {
		entityManager.persist(ticket);
		return ticket;
	}

	@Override
	public String deleteTicketById(String id) {
		Ticket ticket=getTicketById(id);
		if(ticket!=null){
			entityManager.remove(ticket);
			return "Ticket by Id: '"+id+"' has been deleted successfully!!";
		}else
		return "Ticket with id '"+id+" does not exist";
	}

	@Override
	public void updateTicketById(Ticket ticket, String ticketId) {
		Ticket ticketFromDB=getTicketById(ticketId);
		if(ticketFromDB!=null){
			String passengerName = ticket.getPassengerName();
			String sourceStation = ticket.getSourceStation();
			String destination = ticket.getDestination();
			Date dateOfJourney = ticket.getDateOfJourney();
			ticketFromDB.setPassengerName(passengerName);
			ticketFromDB.setSourceStation(sourceStation);
			ticketFromDB.setDestination(destination);
			ticketFromDB.setDateOfJourney(dateOfJourney);
		}
	}

	@Override
	public boolean isTicketByIdExist(String id) {
		//String HQL="FROM Ticket as t WHERE t.passengerName =:id";
		//int count=entityManager.createQuery(HQL).getResultList().size();
		Query query=entityManager.createQuery("SELECT t FROM Ticket t WHERE t.passengerName = ?");
		query.setParameter(1, id);
		int count=query.getResultList().size();
		return count > 0 ? true:false;
	}

}
