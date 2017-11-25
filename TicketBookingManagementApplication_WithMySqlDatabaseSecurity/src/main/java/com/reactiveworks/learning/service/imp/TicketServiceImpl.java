package com.reactiveworks.learning.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.dao.ITicketDAO;
import com.reactiveworks.learning.entity.Ticket;
import com.reactiveworks.learning.service.ITicketService;
/**
 * SEVICE LAYER
 * Implementation Class for Service layer
 * Implementing ITicketService Interface
 * Autowiring the DAO Layer Interface
 * @author Md Noorshid
 *
 */
@Service
public class TicketServiceImpl implements ITicketService {
	
	@Autowired //DAO Layer Interface
    ITicketDAO iTicketDAO;

	@Override
	public List<Ticket> getAllTickets() {
		return iTicketDAO.getAllTickets();
	}

	@Override
	public Ticket getTicketById(String name) {
		return iTicketDAO.getTicketById(name);
	}

	@Override
	public boolean createTicket(Ticket ticket) {
		if(iTicketDAO.isTicketByIdExist(ticket.getPassengerName())){
			return false;
		}else{
			iTicketDAO.createTicket(ticket);
			return true;
		}
	}

	@Override
	public void deleteTicketById(String name) {
		iTicketDAO.deleteTicketById(name);
	}

	@Override
	public void updateTicket(Ticket ticket, String name) {
		 iTicketDAO.updateTicketById(ticket, name);;
	}

}
