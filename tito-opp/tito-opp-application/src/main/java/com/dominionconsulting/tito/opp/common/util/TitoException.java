package com.dominionconsulting.tito.opp.common.util;

public class TitoException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

    private final String errorMessage;
    
    public TitoException(String errorMessage)
    {
    	super(errorMessage);
    	this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage()
    {
    	return this.errorMessage;
    }
}
