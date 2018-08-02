package com.podio.integration.freshdesk.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.podio.integration.freshdesk.beans.Contact;
import com.podio.integration.freshdesk.beans.Description;
import com.podio.integration.freshdesk.beans.Fields;
import com.podio.integration.freshdesk.beans.PodioTicketFields;
import com.podio.integration.freshdesk.beans.Subject;
import com.podio.integration.freshdesk.beans.Ticket;
import com.podio.integration.freshdesk.config.RestClientConfig;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
@Configuration
@PropertySource("classpath:url.properties")
public class FreshdeskService {

	@Autowired
	public RestClientConfig restClientConfig;
	@Autowired
    private Environment env;
	@Autowired
	public PodioService podioService;

	public List<Contact> getAllContacts() {

		final String uri = env.getProperty("allConatctsURL");
		RestTemplate restTemplate = restClientConfig.restTemplate();

		HttpEntity<String> entity = new HttpEntity<String>(restClientConfig.createHeaders());
		ResponseEntity<List<Contact>> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Contact>>() {
				});
		
		System.out.println(result.getBody());

		return result.getBody();

	}
	
	public Contact getCurrentContact(long id) {
		final String uri = env.getProperty("singleContactURL") + id;
		RestTemplate restTemplate = restClientConfig.restTemplate();

		HttpEntity<String> entity = new HttpEntity<String>(restClientConfig.createHeaders());
		ResponseEntity<Contact> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<Contact>() {
				});
		
		System.out.println(result.getBody());

		return result.getBody();
	}
	
	public List<Ticket> getAllTickets() {
		final String uri = env.getProperty("allTicketURL");
		RestTemplate restTemplate = restClientConfig.restTemplate();

		HttpEntity<String> entity = new HttpEntity<String>(restClientConfig.createHeaders());
		ResponseEntity<List<Ticket>> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Ticket>>() {
				});
		
		System.out.println(result.getBody());

		return result.getBody();
	}
	
	public Ticket getCurrentTicket(int ticketId) {
		final String uri = env.getProperty("singleTicketURL") +  ticketId;
		RestTemplate restTemplate = restClientConfig.restTemplate();

		HttpEntity<String> entity = new HttpEntity<String>(restClientConfig.createHeaders());
		ResponseEntity<Ticket> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<Ticket>() {
				});
		
		System.out.println(result.getBody());

		return result.getBody();
	}
	
	
}
