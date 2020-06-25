package com.exchange.services;

import java.util.List;

import com.exchange.model.ExchangeRateReq;
import com.exchange.model.ExchangeRateRes;


public interface CurrencyExchangeService 
{
	 /**
	   * This method give rate
	   * @param ExchangeRateReq exchangeRate
	   * @return List<ExchangeRateRes> 
	   */	 
	List<ExchangeRateRes> getRate(ExchangeRateReq exchangeRate);
	
}
