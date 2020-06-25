package com.exchange.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.exchange.model.ExchangeRateRes;

/*This class use to build business logic. */
public class ExchangeHelper 
{
    private static  String  ASCENDING = "ASCENDING" ;
    private static  String  DESCENDING = "DESCENDING" ;
    private static  String  CONSTANT = "CONSTANT" ;
    private static  String  UNDEFINED = "UNDEFINED" ;
	
	public static List<ExchangeRateRes> sortedByDate(List<ExchangeRateRes> rateList)
	{
		List<ExchangeRateRes> ratelist =	rateList.stream().sorted((r1,r2) ->r1.getDate().compareTo(r2.getDate()) ).collect(Collectors.toList());
		Collections.reverse(ratelist);
		List<ExchangeRateRes> reverseList = new ArrayList<ExchangeRateRes>();
		for(ExchangeRateRes er:ratelist)
		{
			if(reverseList.size()<5 )
			 {reverseList.add(er);}
		}
	
		return reverseList;
	}
	
	public static float averageRate(List<ExchangeRateRes> rateList)
	{
		float avg =0.000000f;
		for (ExchangeRateRes exRate : rateList)
		{
			avg = avg + exRate.getRate();
		}
		return avg/5;
	}
	
	public static String trendRate(List<ExchangeRateRes> exchangeRates)
    {
		Collections.reverse(exchangeRates);
		Map<String,String> trendMap = new HashMap<>();
	    for (int i=0;i<4;i++)
	    {
	       System.out.println("Date---- "+exchangeRates.get(i).getDate()+"   "+exchangeRates.get(i).getRate());
	    	if(exchangeRates.get(i).getRate() < exchangeRates.get(i+1).getRate())
	        {
	            trendMap.put("ASC",ASCENDING);
	        }
	        if(exchangeRates.get(i).getRate() > exchangeRates.get(i+1).getRate())
	        {
	            trendMap.put("DSC",DESCENDING);
	        }
	        if(exchangeRates.get(i).getRate() == exchangeRates.get(i+1).getRate())
	        {
	            trendMap.put("CON",CONSTANT);
	        }

	    }
	  return trending( trendMap );
	}

	private static String  trending(Map<String,String> trendMap)
	{
	    String trendRate = UNDEFINED ;
	   if ( !trendMap.isEmpty() && trendMap.size()>1 )

	   {
	       return  trendRate;
	   }
	   else
	       {
	           String[]  strArr = {"ASC","DSC","CON" };
	           for (int i=0;i<3;i++)
	           {
	               if (trendMap.containsKey(strArr[i]))
	               {
	                   trendRate =  trendMap.get(strArr[i]);
	               }
	           }
	   }
	    return trendRate ;
	}
	
}
