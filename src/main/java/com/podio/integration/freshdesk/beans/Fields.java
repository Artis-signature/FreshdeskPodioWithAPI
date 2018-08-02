package com.podio.integration.freshdesk.beans;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Fields
{
	@JsonProperty("174718536")
    private Subject[] subject;
	
	@JsonProperty("174718537")
	private Description[] description;
	
	@JsonProperty("174718538")
	private TicketID[] ticketId;
	
	@JsonProperty("174718539")
	private Status[] status;
	
	@JsonProperty("174718540")
	private Type[] type;
	
	@JsonProperty("174718541")
	private Priority[] priority;

	public Subject[] getSubject() {
		return subject;
	}
	
	@JsonProperty("174718536")
	public void setSubject(Subject[] subject) {
		this.subject = subject;
	}

	public Description[] getDescription() {
		return description;
	}

	@JsonProperty("174718537")
	public void setDescription(Description[] description) {
		this.description = description;
	}

	public TicketID[] getTicketId() {
		return ticketId;
	}

	@JsonProperty("174718538")
	public void setTicketId(TicketID[] ticketId) {
		this.ticketId = ticketId;
	}

	public Status[] getStatus() {
		return status;
	}

	public void setStatus(Status[] status) {
		this.status = status;
	}

	public Type[] getType() {
		return type;
	}

	@JsonProperty("174718540")
	public void setType(Type[] type) {
		this.type = type;
	}

	public Priority[] getPriority() {
		return priority;
	}

	@JsonProperty("174718541")
	public void setPriority(Priority[] priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Fields [subject=" + Arrays.toString(subject) + ", description=" + Arrays.toString(description)
				+ ", ticketId=" + Arrays.toString(ticketId) + ", status=" + Arrays.toString(status) + ", type="
				+ Arrays.toString(type) + ", priority=" + Arrays.toString(priority) + "]";
	}

	
	
	
}
