package com.reactiveworks.learning.dao;

import java.util.List;

import com.reactiveworks.learning.entity.Ticket;

/**
 * TicketDAO Interface
 * @author Niyamat
 *
 */
public interface ITicketDAO{
public List<Ticket> getAllTickets();
public Ticket getTicketById(String name);
public Ticket createTicket(Ticket ticket);
public String deleteTicketById(String id);
public void updateTicketById(Ticket ticket,String ticketId);
public boolean isTicketByIdExist(String id);
}
