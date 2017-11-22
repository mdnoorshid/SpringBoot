package com.reactiveworks.learning.app;


import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TicketBookingManagementAppApplication {
      
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =SpringApplication.run(TicketBookingManagementAppApplication.class, args);
		/*TicketBookingService ticketBookingService = applicationContext.getBean("ticketBookingService", TicketBookingService.class);
		Ticket ticket=new Ticket();
		ticket.setPassengerName("Md Noorshid");
		ticket.setSourceStation("Bangalore");
		ticket.setDestination("Kolkata");
		ticket.setBookingDate(new Date());
		ticket.setEmail("mdnoorshid254@gmail.com");
		ticketBookingService.createTicket(ticket);*/
	}
}
