package com.podio.integration.freshdesk.beans;

public class TicketID {
	
	private int value;

    public int getValue ()
    {
    	System.out.println(value);
        return value;
    }

    public void setValue (int value)
    {
    	System.out.println("In ticket id set method");
        this.value = value;
        System.out.println(this.value);
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+"]";
    }

}
