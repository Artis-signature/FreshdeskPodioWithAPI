package com.podio.integration.freshdesk.beans;

public class Category {
	
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
        return "ClassPojo [value = "+value+"]";
    }

}
