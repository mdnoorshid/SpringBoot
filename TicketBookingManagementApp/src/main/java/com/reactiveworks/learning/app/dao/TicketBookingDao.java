package com.reactiveworks.learning.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reactiveworks.learning.app.entity.Ticket;

public interface TicketBookingDao extends JpaRepository<Ticket, Integer>{
public List<Ticket> findByPassengerName(String name);
public List<Ticket> findByBookingDate(Date bookingDate);
public List<Ticket> findByPassengerNameLike(String passengerName);
public List<Ticket> getTicketDetailsByDestination(String destination);

/**
 * This method is demo for defining Query in Dao itself and make Domain class(Here Ticket) free from java persistence
 * Here we have both options whether the Query is based on JPQL or Native query.
 * For Native query nativeQuery should set to be true
 * @param sourceStation
 * @return
 */
@Query(value="SELECT t from Ticket t where t.sourceStation=?1")
public List<Ticket> findBySourceStationInfo(String sourceStation);

}
