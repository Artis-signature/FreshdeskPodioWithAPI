package com.podio.integration.freshdesk.beans;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TotalFilteredData {
	
	private int total;

    private int filtered;
    
    private List<Items> items;


	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public int getTotal ()
    {
        return total;
    }

    public void setTotal (int total)
    {
        this.total = total;
    }

    public int getFiltered ()
    {
        return filtered;
    }

    public void setFiltered (int filtered)
    {
        this.filtered = filtered;
    }

	@Override
	public String toString() {
		return "TotalFilteredData [total=" + total + ", filtered=" + filtered + ", items=" + items + "]";
	}

	



}
