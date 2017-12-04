package com.reactiveworks.learning.dao;

import org.springframework.data.repository.CrudRepository;

import com.reactiveworks.learning.entity.Ticket;

public interface TicketDAO extends CrudRepository<Ticket, Integer> {
	Ticket findByEmail(String email);

}
