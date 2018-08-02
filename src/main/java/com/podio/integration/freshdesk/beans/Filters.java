package com.podio.integration.freshdesk.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Filters {

	private FilterByTicketID ticketId;
	
	@JsonProperty("174718538")
	public FilterByTicketID getTicketId() {
		return ticketId;
	}
	
	@JsonProperty("174718538")
	public void setTicketId(FilterByTicketID ticketId) {
		this.ticketId = ticketId;
	}

   

}
