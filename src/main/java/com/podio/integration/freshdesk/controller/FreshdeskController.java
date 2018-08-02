package com.podio.integration.freshdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.podio.integration.freshdesk.beans.Contact;
import com.podio.integration.freshdesk.beans.Description;
import com.podio.integration.freshdesk.beans.Fields;
import com.podio.integration.freshdesk.beans.PodioTicketFields;
import com.podio.integration.freshdesk.beans.Subject;
import com.podio.integration.freshdesk.beans.Ticket;
import com.podio.integration.freshdesk.services.FreshdeskService;
import com.podio.integration.freshdesk.services.PodioService;

@RestController
public class FreshdeskController {
	
	@Autowired
	public FreshdeskService freshdeskService;
	@Autowired
	public PodioService podioService;
	@Autowired
	public PodioController podioController;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> getAllContacts() {
		
		System.out.println("In freshdesk connection");
		Object contacts = freshdeskService.getAllContacts();
		List<Contact> contact = (List<Contact>) contacts;
		return contact;
	}
	
	@RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.GET)
	public Contact getCurrentContact(@PathVariable("contactId") long contactId){
		System.out.println("In single contact");
		Object object = freshdeskService.getCurrentContact(contactId);
		Contact currentContact = (Contact)object;
		return currentContact;

	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public List<Ticket> getAllTickets(){
		System.out.println("In getAllTickets");
		Object object = freshdeskService.getAllTickets();
		List<Ticket> tickets = (List<Ticket>)object;
		
		return tickets;
	}
	
	@RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET)
	public Ticket getCurrentTicket(@PathVariable("ticketId") int ticketId) {
		System.out.println("In single ticket");
		Object object = freshdeskService.getCurrentTicket(ticketId);
		Ticket currentTicket = (Ticket) object;
		System.out.println(currentTicket);
		System.out.println(currentTicket.getSubject());
		System.out.println(currentTicket.getDescription_text());
		
		podioService.addTicketPodio(currentTicket);
		return currentTicket;
	}
	
	@RequestMapping(value = "/ticket", method = RequestMethod.POST)
	public void createTickets(@RequestBody Ticket ticket) {
		System.out.println("In create ticket");
		System.out.println(ticket);
		
	}
	

}
