package com.reactiveworks.learning.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_security")
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="passenger_ticket_id")
	private Integer id;
	@Column(name="passenger_name",length=30,nullable=false)
	private String name;
	@Column(name="source_station", length=10, nullable=false)
	private String sourceStation;
	@Column(name="destination", length=10, nullable=false)
	private String destination;
	@Column(name="date_of_journey", length=10, nullable=false)
	private Date dateOfJourney;
	@Column(name="passenger_email", length=50, nullable=false)
	private String email;
	public Ticket(String name, String sourceStation,String destination,  Date dateOfJourney, String email) {
		super();
		this.name = name;
		this.sourceStation = sourceStation;
		this.dateOfJourney = dateOfJourney;
		this.email = email;
		this.destination=destination;
	}
	
	public Ticket() {
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

	public Date getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	

}
