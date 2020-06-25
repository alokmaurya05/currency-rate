package com.exchange.servicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exchange.externalUrl.ExternalUrl;
import com.exchange.model.ExchangeRateReq;
import com.exchange.model.ExchangeRateRes;
import com.exchange.services.CurrencyExchangeService;


/*This Class is use for give the exchange rate*/
@Configuration
@PropertySource("classpath:externalurl.properties")
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService
{

	@Autowired
	private RestTemplate restClient;
	@Autowired
	private ExternalUrl externalAPIUrl ;
	
	@Value("${externalurl.currencyhistory}")
	private String urlPattern;

	@Override
	public List<ExchangeRateRes> getRate(ExchangeRateReq exchangeRate)
	{
		List<ExchangeRateRes>  rateList =new ArrayList<ExchangeRateRes>();
		
	    String Start_date = "";
	    LocalDate date = LocalDate.parse(exchangeRate.getDate());
	    date = date.minusDays(10);
	    Start_date =  date.toString();
	    List<String> parameterList= new ArrayList<String>();
	    parameterList.add(Start_date);
	    parameterList.add(exchangeRate.getDate());
	    parameterList.add(exchangeRate.getBaseCurrency());
	   
		ResponseEntity<String>  entity = restClient.getForEntity(externalAPIUrl.getUrl(urlPattern,parameterList), String.class);
	     String strRes= entity.getBody();
	     JSONObject jsonObj =new JSONObject(strRes);
	     JSONObject rates= jsonObj.getJSONObject("rates");
	     if(rates.has(exchangeRate.getDate()))
	     {	 
	     for(int i=0;i<=10;i++)
	     {
	    	LocalDate localdate = date;
	    	localdate =localdate.plusDays(i);
	 	   String  onDate =  localdate.toString();
	 		
	    	if(rates.has(onDate)) 
	    	{
	    	 JSONObject dateObj = rates.getJSONObject(onDate);
	    	 ExchangeRateRes exRate =new ExchangeRateRes();
	    	 
	   	     Float currency = dateObj.getFloat(exchangeRate.getTargetCurrency());
	   	     exRate.setDate(LocalDate.parse(onDate));
	   	     exRate.setRate(currency);
	    	 
	   	     rateList.add(exRate);
	    	}    	 
	    	 
	     }
		}
		
		return rateList;
	}

	
}
