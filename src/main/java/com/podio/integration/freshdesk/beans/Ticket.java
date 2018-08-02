package com.podio.integration.freshdesk.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Ticket {
	
	@JsonProperty("id")
	private int ticketId;
	private String subject;
	private String description_text;
	private int status;
	private String type;
	private int priority;
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription_text() {
		return description_text;
	}
	public void setDescription_text(String description_text) {
		this.description_text = description_text;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", subject=" + subject + ", description_text=" + description_text
				+ ", status=" + status + ", type=" + type + ", priority=" + priority + "]";
	}
	
	
}
