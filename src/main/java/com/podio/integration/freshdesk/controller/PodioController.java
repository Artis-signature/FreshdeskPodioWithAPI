package com.podio.integration.freshdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.podio.integration.freshdesk.beans.PodioTicketFields;
import com.podio.integration.freshdesk.services.PodioService;

@RestController
public class PodioController {

	@Autowired
	public PodioService podioService;
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addItem(@RequestBody PodioTicketFields fields) {
		System.out.println("Inside addItem");
		
		return "success";
	}
	
}
