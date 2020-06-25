package com.exchange.error;

/**
 * This class throw Exception 
 * When Requested Date is Sunday ,Saturday and holiday
 */
public class NoDataOnSatSunException extends RuntimeException
{

		private static final long serialVersionUID = 1L;
	  
		public NoDataOnSatSunException(String notFound)
		{
			super("No Data Available on Sunday ,Saturday and holiday " + notFound );
		}

			
		
}
