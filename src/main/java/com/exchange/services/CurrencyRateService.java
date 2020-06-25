package com.exchange.services;


import java.util.Date;
import java.util.List;

import com.exchange.entity.CurrencyExchangeEntity;

public interface CurrencyRateService 
{
	  /**
	   * This method save entity
	   * @param CurrencyExchangeEntity entity
	   */	
	public void save(CurrencyExchangeEntity entity);
	
	  /**
	   * This method give Daily rates
	   * @param Date date
	   * @return List<CurrencyExchangeEntity>
	   */	
	public List<CurrencyExchangeEntity> getDailyRate(Date date);
	  /**
	   * This method give Monthly rates
	   * @param Date satrtDate
	   * @param Date endDate
	   * @return List<CurrencyExchangeEntity>
	   */	
	public List<CurrencyExchangeEntity> getMonthlyRate(Date satrtDate, Date endDate);
	
}
