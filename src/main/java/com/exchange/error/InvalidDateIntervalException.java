package com.exchange.error;

import java.time.LocalDate;

/**
 * This class throw Exception 
 * When Enter Date in not in between 2000-01-01 and yesterday
 */
public class InvalidDateIntervalException extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public InvalidDateIntervalException(String invalidDate)
	{
		super("Date should be in between 2000-01-01 and "+ LocalDate.now().minusDays(1) +" Not  " + invalidDate);
	}

}
