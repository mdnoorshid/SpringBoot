package com.reactiveworks.learning.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_id")
	private Integer id;
	@Column(name = "passenger_name")
	private String name;
	@Column(name = "source_station")
	private String sourceStation;
	@Column(name = "booking_date")
	private Date bookingDate;
	@Column(name = "destination")
	private String destination;
	@Column(name = "email")
	private String email;

	public Ticket() {
	}

	public Ticket(Integer id, String name, String sourceStation, Date bookingDate, String destination,
			String email) {
		this.id = id;
		this.name = name;
		this.sourceStation = sourceStation;
		this.bookingDate = bookingDate;
		this.destination = destination;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
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
		return "PersonModel [id=" + id + ", name=" + name + ", sourceStation=" + sourceStation + ", bookingDate="
				+ bookingDate + ", destination=" + destination + ", email=" + email + "]";
	}

}
