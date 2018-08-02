package com.podio.integration.freshdesk.config;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

	@Bean
    public RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate;
	}
	
	
	public HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();

		String auth = "projteam@artissol.com" + ":" + "200By2018";
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);

		headers.add("Authorization", authHeader);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}
	
}
