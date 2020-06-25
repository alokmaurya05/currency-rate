package com.exchange.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.entity.CurrencyExchangeEntity;
import com.exchange.repository.CurrencyRateRepository;
import com.exchange.services.CurrencyRateService;

import lombok.NonNull;

/*This Class give rate service */
@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {
	
	@Autowired
	private final CurrencyRateRepository repository;

	public CurrencyRateServiceImpl(CurrencyRateRepository repository) {
		this.repository = repository;

	}

	@Override
	public void save(@NonNull CurrencyExchangeEntity entity) {
		repository.save(entity);
	}

	@Override
	public List<CurrencyExchangeEntity> getDailyRate(Date date) {

		return repository.findAllByDate(date);
	}

	@Override
	public List<CurrencyExchangeEntity> getMonthlyRate(Date startDate, Date endDate) {

		return repository.findAllByDateBetween(startDate, endDate);
	}

}
