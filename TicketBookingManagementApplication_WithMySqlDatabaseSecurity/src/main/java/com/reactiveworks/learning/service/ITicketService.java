package com.reactiveworks.learning.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.reactiveworks.learning.entity.Ticket;
/**
 * Service Interface for TicketService
 * @author Niyamat
 *
 */
public interface ITicketService {
  @Secured({"ROLE_ADMIN"})	
  public List<Ticket> getAllTickets();
  @Secured({"ROLE_ADMIN","ROLE_USER"})
  public Ticket getTicketById(String name);
  @Secured({"ROLE_ADMIN","ROLE_USER"})
  public boolean createTicket(Ticket ticket);
  @Secured({"ROLE_ADMIN"})
  public void deleteTicketById(String name);
  @Secured({"ROLE_ADMIN","ROLE_USER"})
  public void updateTicket(Ticket ticket,String name);
}
