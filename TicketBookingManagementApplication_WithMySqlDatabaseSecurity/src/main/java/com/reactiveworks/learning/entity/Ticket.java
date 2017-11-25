package com.reactiveworks.learning.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for Ticket
 * @author Md Noorshid
 */
@Entity
@Table(name="ticket_security",schema="TicketSecurityDB")
public class Ticket {

@Id
@Column(name="passenger_name",length=40,nullable=false)
private String passengerName;
@Column(name="source_station",length=40,nullable=false)
private String sourceStation;
@Column(name="destination",length=40,nullable=false)
private String destination;
@Column(name="date_of_journey",nullable=false)
private Date dateOfJourney;
@Column(name="passenger_email")
private String email;
public Ticket(String passengerName, String sourceStation, String destination, Date dateOfJourney, String email) {
	super();
	this.passengerName = passengerName;
	this.sourceStation = sourceStation;
	this.destination = destination;
	this.dateOfJourney = dateOfJourney;
	this.email = email;
}

public Ticket() {
}

public String getPassengerName() {
	return passengerName;
}

public void setPassengerName(String passengerName) {
	this.passengerName = passengerName;
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
}
