package com.podio.integration.freshdesk.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class TotalFilteredItems {
	
	private FilteredData filtered;

	public FilteredData getFiltered() {
		return filtered;
	}

	public void setFiltered(FilteredData filtered) {
		this.filtered = filtered;
	}
	
	

}
