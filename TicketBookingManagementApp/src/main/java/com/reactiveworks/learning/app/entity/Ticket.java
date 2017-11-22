package com.reactiveworks.learning.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This is the entity class for Ticket booking
 * @author Md Noorshid
 */
@Entity
@Table(name="ticket")

//Example Using NamedQueries , where Query is based on Entity Class 9
//We need to write JPQL based query

@NamedQueries(value = {
		@NamedQuery(name="Ticket.findByBookingDate", query="SELECT t FROM Ticket t where t.bookingDate=?1"),
		@NamedQuery(name="Ticket.getTicketDetailsByDestination" ,query="SELECT t FROM Ticket t where t.destination=?1")
		})

//Example Using NamedNativeQueries , where Query is based directly on Table
//We need to write SQL database query

@NamedNativeQueries(value={
	@NamedNativeQuery(name="Ticket.getTicketByPassengetNameLike" , query="SELECT * FROM ticket WHERE passenger_name LIKE %?1", resultClass=Ticket.class)
})
public class Ticket {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ticket_id")
	private Integer ticketId;
	@Column(name="passenger_name")
	private String passengerName;
	@Column(name="booking_date")
	private Date bookingDate;
	@Column(name="source_station")
	private String sourceStation;
	@Column(name="destination")
	private String destination;
	@Column(name="email")
	private String email;
	
	public Ticket(Integer ticketId, String passengerName, Date bookingDate, String sourceStation, String destination,String email) {
		this.ticketId = ticketId;
		this.passengerName = passengerName;
		this.bookingDate = bookingDate;
		this.sourceStation = sourceStation;
		this.destination = destination;
		this.email=email;
	}
	public Ticket() {
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", passengerName=" + passengerName + ", bookingDate=" + bookingDate
				+ ", sourceStation=" + sourceStation + ", destination=" + destination + ", email=" + email + "]";
	}
	
	
}
