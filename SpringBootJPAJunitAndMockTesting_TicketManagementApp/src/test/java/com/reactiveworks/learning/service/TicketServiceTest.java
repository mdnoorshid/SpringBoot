package com.reactiveworks.learning.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactiveworks.learning.dao.TicketDAO;
import com.reactiveworks.learning.entity.Ticket;

/**
 * Mockito is just assumption where we assume the required output when we give certain kind of inputs
 * without interacting the database.
 * It is set into the MocketBean Object.
 * Here it is set into TicketDAO bean.
 * We set the values of mockBean object there only where our service layer method is going to interact with
 * mockBean object method
 * 
 * @author Md Noorshid
 *
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
	static Logger logger=Logger.getLogger(TicketServiceTest.class);

	@Autowired
	TicketService ticketService;

	@MockBean
	TicketDAO ticketDAO;

	@Test
	public void addTicketTest() {
		Ticket ticket = new Ticket();
		ticket.setName("Tiger Zinda Hai");
		ticket.setSourceStation("Bangalore");
		ticket.setDestination("Mumbai");
		ticket.setBookingDate(new Date());
		ticket.setEmail("tiger123@gmail.com");
		Mockito.when(ticketDAO.save(ticket)).thenReturn(ticket);

		assertThat(ticketService.addTicket(ticket)).isEqualTo(ticket);
	}

	@Test
	public void getAllTicketsTest() {
		Ticket ticket1 = new Ticket();
		ticket1.setName("Tiger Zinda Hai");
		ticket1.setSourceStation("Bangalore");
		ticket1.setDestination("Mumbai");
		ticket1.setBookingDate(new Date());
		ticket1.setEmail("tiger123@gmail.com");

		Ticket ticket2 = new Ticket();
		ticket1.setName("Srk");
		ticket1.setSourceStation("Bangalore");
		ticket1.setDestination("Mumbai");
		ticket1.setBookingDate(new Date());
		ticket1.setEmail("srk123@gmail.com");

		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList.add(ticket1);
		ticketList.add(ticket2);
		Mockito.when(ticketDAO.findAll()).thenReturn(ticketList);

		assertThat(ticketService.getAllTickets()).isEqualTo(ticketList);

	}

	@Test
	public void getTicketTest() {
		Ticket ticket1 = new Ticket();
		ticket1.setId(1);
		ticket1.setName("akki");
		ticket1.setSourceStation("Bangalore");
		ticket1.setDestination("Mumbai");
		ticket1.setBookingDate(new Date());
		ticket1.setEmail("akki@gmail.com");

		Mockito.when(ticketDAO.findOne(1)).thenReturn(ticket1);
        logger.info("Ticket From DB:: "+ticketDAO.findAll());
		assertThat(ticketService.getTicket(1)).isEqualTo(ticket1);

	}
	
	@Test
	public void deleteTicketTest(){
		Ticket ticket1 = new Ticket();
		ticket1.setId(1);
		ticket1.setName("aamir");
		ticket1.setSourceStation("Bangalore");
		ticket1.setDestination("Mumbai");
		ticket1.setBookingDate(new Date());
		ticket1.setEmail("aamir@gmail.com");

		Mockito.when(ticketDAO.findOne(1)).thenReturn(ticket1);
		Mockito.when(ticketDAO.exists(ticket1.getId())).thenReturn(false);
		assertFalse(ticketDAO.exists(ticket1.getId()));
		
	}
	
	@Test
	@Ignore
	public void updateTicket(){
		Ticket ticket1 = new Ticket();
		ticket1.setId(1);
		ticket1.setName("ajay");
		ticket1.setSourceStation("Bangalore");
		ticket1.setDestination("Mumbai");
		ticket1.setBookingDate(new Date());
		ticket1.setEmail("ajay@gmail.com");
		
		Mockito.when(ticketDAO.findOne(1)).thenReturn(ticket1);
		ticket1.setEmail("ajayDevgan@gmail.com");
		
		Mockito.when(ticketDAO.save(ticket1)).thenReturn(ticket1);
		
		assertThat(ticketService.updateTicket(1, ticket1)).isEqualTo(ticket1);
	}
	
	@Test
	public void findByEmailTest(){
		Ticket ticket1 = new Ticket();
		ticket1.setId(1);
		ticket1.setName("Kriti");
		ticket1.setSourceStation("Bangalore");
		ticket1.setDestination("Mumbai");
		ticket1.setBookingDate(new Date());
		ticket1.setEmail("kriti@gmail.com");
		
		Mockito.when(ticketDAO.findByEmail("kriti@gmail.com")).thenReturn(ticket1);
		
		assertThat(ticketService.findByEmail("kriti@gmail.com")).isEqualTo(ticket1);
		
	}
	

}
