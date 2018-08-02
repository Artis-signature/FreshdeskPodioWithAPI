package com.podio.integration.freshdesk.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class PodioTicketFields {
	
	 private Fields fields;

	    public Fields getFields ()
	    {
	        return fields;
	    }

	    public void setFields (Fields fields)
	    {
	        this.fields = fields;
	    }

		@Override
		public String toString() {
			return "PodioTicketFields [fields=" + fields + "]";
		}

	    
	

}
