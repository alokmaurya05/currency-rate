package com.exchange.error;


/**
 * This class is use for throw Exception when invalid Date for is Enter
 */
public class InvalidDateFormateException extends RuntimeException
{
	
	private static final long serialVersionUID = -5341252641739160947L;

	public InvalidDateFormateException(String invalidDate)
	{
		super("Invalid Date Formate, should be Like 2020-04-01 Not " + invalidDate);
	}
	
}
