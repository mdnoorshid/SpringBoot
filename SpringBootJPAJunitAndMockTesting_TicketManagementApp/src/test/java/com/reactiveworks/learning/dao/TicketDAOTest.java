package com.reactiveworks.learning.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactiveworks.learning.entity.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Ignore
public class TicketDAOTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TicketDAO ticketDAO;

	@Test
	public void testSaveTicket() {
		Ticket ticket = getTicket();
		Ticket savedInDB = entityManager.persist(ticket);
		Ticket getFromDB = ticketDAO.findOne(savedInDB.getId());
		assertThat(getFromDB).isEqualTo(savedInDB);
	}

	@Test
	public void testGetTicketById() {
		Ticket ticket = getTicket();
		Ticket savedInDB = entityManager.persist(ticket);
		Ticket getFromDB = ticketDAO.findOne(savedInDB.getId());
		assertThat(getFromDB).isEqualTo(savedInDB);
	}

	@Ignore
	@Test
	public void testGetAllTickets() {
		Ticket ticket1 = getTicket();
		Ticket ticket2 = new Ticket();
		ticket2.setName("Shruti");
		ticket2.setSourceStation("Bangalore");
		ticket2.setDestination("Kolkata");
		ticket2.setBookingDate(new Date());
		ticket2.setEmail("sk@gmail.com");

		// Both Saving in DB
		entityManager.persist(ticket1);
		entityManager.persist(ticket2);

		Iterable<Ticket> ticketItr = ticketDAO.findAll();
		List<Ticket> allTicketList = new ArrayList<Ticket>();

		for (Ticket ticket : ticketItr) {
			allTicketList.add(ticket);
		}

		assertThat(allTicketList.size()).isEqualTo(2);
	}

	@Test
	public void testFindByEmail() {
		Ticket ticket = getTicket();
		entityManager.persist(ticket);
		Ticket ticketFromDB = ticketDAO.findByEmail("mdnoorshid254@gmail.com");
		assertThat(ticketFromDB.getName()).isEqualTo("Md Noorshid");
	}

	@Test
	public void testDeleteTicket() {
		Ticket ticket1 = getTicket();
		Ticket ticket2 = new Ticket();
		ticket2.setName("Shruti");
		ticket2.setSourceStation("Bangalore");
		ticket2.setDestination("Kolkata");
		ticket2.setBookingDate(new Date());
		ticket2.setEmail("sk@gmail.com");

		// Both Saving in DB
		Ticket persist = entityManager.persist(ticket1);
		entityManager.persist(ticket2);

		entityManager.remove(persist);

		Iterable<Ticket> ticketItr = ticketDAO.findAll();
		List<Ticket> allTicketList = new ArrayList<Ticket>();

		for (Ticket ticket : ticketItr) {
			allTicketList.add(ticket);
		}

		assertThat(allTicketList.size()).isEqualTo(1);

	}
	
	@Test
	public void testUpdateTicket(){
		Ticket ticket1 = getTicket();
		entityManager.persist(ticket1);
		Ticket ticketFromDB=ticketDAO.findByEmail("mdnoorshid254@gmail.com");
		ticketFromDB.setEmail("mdnoorshid2017@gmail.com");
		entityManager.persist(ticketFromDB);
	    assertThat(ticketFromDB.getEmail()).isEqualTo("mdnoorshid2017@gmail.com");
	}
	
	

	private Ticket getTicket() {
		Ticket ticket = new Ticket();
		ticket.setName("Md Noorshid");
		ticket.setSourceStation("Bangalore");
		ticket.setDestination("Kolkata");
		ticket.setBookingDate(new Date());
		ticket.setEmail("mdnoorshid254@gmail.com");
		return ticket;
	}

}
