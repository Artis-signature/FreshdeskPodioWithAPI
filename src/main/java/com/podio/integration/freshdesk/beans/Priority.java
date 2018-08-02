package com.podio.integration.freshdesk.beans;

public class Priority {
	
	private int value;

    public int getValue ()
    {
        return value;
    }

    public void setValue (int value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Priority [value = "+value+"]";
    }

}
