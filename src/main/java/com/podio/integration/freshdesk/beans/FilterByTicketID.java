package com.podio.integration.freshdesk.beans;

public class FilterByTicketID {
	
	private int to;

    private int from;

    public int getTo ()
    {
        return to;
    }

    public void setTo (int to)
    {
        this.to = to;
    }

    public int getFrom ()
    {
        return from;
    }

    public void setFrom (int from)
    {
        this.from = from;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [to = "+to+", from = "+from+"]";
    }

}
