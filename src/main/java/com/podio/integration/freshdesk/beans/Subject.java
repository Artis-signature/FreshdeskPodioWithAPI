package com.podio.integration.freshdesk.beans;

public class Subject {
	
	private String value;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Subject [value = "+value+"]";
    }

}
