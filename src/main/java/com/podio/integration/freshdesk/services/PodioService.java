package com.podio.integration.freshdesk.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.podio.APIFactory;
import com.podio.ResourceFactory;
import com.podio.integration.freshdesk.beans.Description;
import com.podio.integration.freshdesk.beans.Fields;
import com.podio.integration.freshdesk.beans.Filter;
import com.podio.integration.freshdesk.beans.FilterByTicketID;
import com.podio.integration.freshdesk.beans.Filters;
import com.podio.integration.freshdesk.beans.Items;
import com.podio.integration.freshdesk.beans.PodioTicketFields;
import com.podio.integration.freshdesk.beans.Priority;
import com.podio.integration.freshdesk.beans.Status;
import com.podio.integration.freshdesk.beans.Subject;
import com.podio.integration.freshdesk.beans.Ticket;
import com.podio.integration.freshdesk.beans.TicketID;
import com.podio.integration.freshdesk.beans.TotalFilteredData;
import com.podio.integration.freshdesk.beans.Type;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUsernameCredentials;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class PodioService {

	@Autowired
    private Environment env;
	private ResourceFactory podioAPI;
	private static final int TICKET_APP_ID = 21280240;
	
	@EventListener(ApplicationReadyEvent.class)
	public void connectPodio() {
		
		String clientSecret = env.getProperty("clientSecret");
		String clientId = env.getProperty("clientId");
		String username = env.getProperty("username");
		String password = env.getProperty("password");
		
		ResourceFactory resourceFactory = new ResourceFactory(new OAuthClientCredentials(clientId, clientSecret),
		        new OAuthUsernameCredentials(username, password));
		
		APIFactory apiFactory = new APIFactory(resourceFactory);
		
//		getPodioTicket(5);
		
	}
	
	
	public Object addItem(PodioTicketFields fields) {
		System.out.println("Inside podio service addItem");
		System.out.println(fields);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		String authHeader = "Bearer c379808eeda047cdaf12776fade0a053";
		headers.add("Authorization", authHeader);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String uri = "https://api.podio.com/item/app/21280240";
		
		HttpEntity<?> entity = new HttpEntity<Object>(fields, headers);
		
		ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				new ParameterizedTypeReference<Object>() {
				});
		System.out.println(result.getBody());
		return result.getBody();
	}
	
	public void addTicketPodio(Ticket currentTicket) {
		System.out.println("In addTicketPodio");
		
		PodioTicketFields fields = new PodioTicketFields();
		Fields field = new Fields();
		Subject[] subject = new Subject[] {new Subject()};
		subject[0].setValue(currentTicket.getSubject());
		
		Description[] description = new Description[] {new Description()};
		description[0].setValue(currentTicket.getDescription_text());
		
		TicketID[] ticketId = new TicketID[] {new TicketID()};
		ticketId[0].setValue(currentTicket.getTicketId());
		
		Status[] status = new Status[] {new Status()};
		status[0].setValue(currentTicket.getStatus() - 1);
		
		Type[] type = new Type[] {new Type()};
		if(currentTicket.getType() == null) {
			type[0].setValue("--");
		}else {
			type[0].setValue(currentTicket.getType());
		}
		
		Priority[] priority = new Priority[] {new Priority()};
		priority[0].setValue(currentTicket.getPriority());
		
		field.setSubject(subject);
		field.setDescription(description);
		field.setTicketId(ticketId);
		field.setStatus(status);
		field.setType(type);
		field.setPriority(priority);
		fields.setFields(field);
		
		Map<String, Object> filteredData= getPodioTicket(currentTicket.getTicketId());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		TotalFilteredData fd = new TotalFilteredData();
		fd.setFiltered(Integer.parseInt(""+filteredData.get("filtered")));
		
		
		
		
		/*for ( Map.Entry<String, Object> entry : filteredData.entrySet()) {
		    String key = entry.getKey();
		    Object tab = entry.getValue();
		    
		    System.out.println(key + " " + tab);
		    if(key.equalsIgnoreCase("filtered")) {
		    	fd.setFiltered(Integer.parseInt(key.valueOf(tab)));
		    }
		    if(key.equalsIgnoreCase("items")) {
//		    	fd.setItems(key.valueOf(tab));
		    }
		    
		}*/
		
		int ticketPresentInPodio = fd.getFiltered();

		if(ticketPresentInPodio == 1) {
			fd.setItems((List<Items>)filteredData.get("items"));
			long itemId = fd.getItems().get(0).getItem_id();
			System.out.println("********************************");
			System.out.println(itemId);
			updateTicketDetails(fields, itemId);
		}else {
			addItem(fields);
		}
	}
	
	public void updateTicketDetails(PodioTicketFields fields, long itemId) {
		
		System.out.println("Inside podio service updateTicketDetails");
		System.out.println(fields);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		String authHeader = "Bearer c379808eeda047cdaf12776fade0a053";
		headers.add("Authorization", authHeader);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String uri = "https://api.podio.com/item/"+itemId;
		
		HttpEntity<?> entity = new HttpEntity<Object>(fields, headers);
		
		ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.PUT, entity,
				new ParameterizedTypeReference<Object>() {
				});
		System.out.println(result.getBody());
		
		
	}
	
	public Map<String, Object> getPodioTicket(int freshdeskTicketID) {
		System.out.println("Inside podio service getPodioTicket");
		System.out.println(freshdeskTicketID);
		Filter filter = new Filter();
		Filters filters = new Filters();
		FilterByTicketID filterId = new FilterByTicketID();
		filterId.setFrom(freshdeskTicketID);
		filterId.setTo(freshdeskTicketID);
		filters.setTicketId(filterId);
		filter.setFilters(filters);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		String authHeader = "Bearer c379808eeda047cdaf12776fade0a053";
		headers.add("Authorization", authHeader);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String uri = "https://api.podio.com/item/app/21280240/filter/";
		
		HttpEntity<?> entity = new HttpEntity<Object>(filter, headers);
		
		ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				new ParameterizedTypeReference<Object>() {
				});
		System.out.println("###################################");
		System.out.println(result.getBody());
		
		Map totalData = (Map)result.getBody();
		System.out.println(totalData);
		return totalData;
	}
	
}
