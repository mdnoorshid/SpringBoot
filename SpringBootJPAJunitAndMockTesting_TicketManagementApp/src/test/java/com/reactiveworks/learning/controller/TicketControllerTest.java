package com.reactiveworks.learning.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.reactiveworks.learning.entity.Ticket;
import com.reactiveworks.learning.service.TicketService;
import com.reactiveworks.learning.test.util.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = Ticket.class, secure = false)
public class TicketControllerTest {
	static Logger logger = Logger.getLogger(TicketControllerTest.class);

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TicketService ticketService;

	@Test
	public void createTicketTest() throws Exception {
		Ticket mockeTicket = new Ticket();
		mockeTicket.setId(1);
		mockeTicket.setName("mdnoorshid");
		mockeTicket.setSourceStation("Bangalore");
		mockeTicket.setDestination("Mumbai");
		mockeTicket.setBookingDate(new Date());
		mockeTicket.setEmail("mdnoorshid@gmail.com");

		String inputInJson = new TestUtil().mapToJson(mockeTicket);
		logger.info("inputInJson-----> "+inputInJson);
		
		String URI="/tickets//createticket";
		Mockito.when(ticketService.addTicket(Mockito.any(Ticket.class))).thenReturn(mockeTicket);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post(URI)
				                      .accept(MediaType.APPLICATION_JSON)
				                      .content(inputInJson)
				                      .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response=result.getResponse();
		
		String outputInJson=response.getContentAsString();
		logger.info("outputInJson:: "+outputInJson);
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertThat(HttpStatus.OK).isEqualTo(response.getStatus());
	}

}
