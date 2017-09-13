package com.dominionconsulting.tito.opp.common.util;

import java.sql.SQLException;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.EmptyResultDataAccessException;
/*
 * Generic Exception Handler for displaying user-friendly message in UI
 */
@ControllerAdvice
public class TitoExceptionHandler
{
	@ExceptionHandler(TitoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException(TitoException exx) 
	{
		// handle ALL TitoException Exceptions thrown
        return exx.getErrorMessage();
    }
	
	@ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException(EntityExistsException exx) 
	{
		// handle ALL EntityExistsException Exceptions thrown
        return "Duplicate Record(s) Already Exists";
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleException(EmptyResultDataAccessException exx) 
	{
		// handle ALL EntityExistsException Exceptions thrown
        return "Record does not Exist";
    }
	
	@ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleException(NoResultException exx) 
	{
		// handle ALL NoResultException Exceptions thrown
        return "No Record(s) found";
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException(IllegalArgumentException exx) 
	{
		// handle ALL IllegalArgumentException Exceptions thrown
        return "Bad Data";
    }
			
	@ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(SQLException exx) 
	{
		// handle database Exceptions thrown
        return "Database Issue. Please contact support";
    }
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleException(HttpRequestMethodNotSupportedException exception) 
	{
		// handle calls to unsupported endpoints
        return "Operation is NOT supported";
    }
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception exx) 
	{
		// handle ALL other types of Exceptions thrown
        return "Internal Server Error. Please retry your request";
    }
}
