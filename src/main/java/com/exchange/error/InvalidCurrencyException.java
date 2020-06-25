package com.exchange.error;
/**
 * This class use to throw exception when Invalid Currency Enter
 */
public class InvalidCurrencyException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

public	InvalidCurrencyException(String currency)
 {
	 super("Invalid Currency Enter, should be like: EUR/INR  Not "+currency);
 }
	
}
