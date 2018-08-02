package com.podio.integration.freshdesk.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class FilteredData {
	
	private int filtered;

	public int getFiltered() {
		return filtered;
	}

	public void setFiltered(int filtered) {
		this.filtered = filtered;
	}

	@Override
	public String toString() {
		return "FilteredData [filtered=" + filtered + "]";
	}
	
	
}
