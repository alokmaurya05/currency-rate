package com.exchange.servicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.exchange.entity.CurrencyExchangeEntity;
import com.exchange.model.ExchangeRateRes;
import com.exchange.rates.RatesApplication;
import com.exchange.repository.CurrencyRateRepository;
import com.exchange.services.CurrencyRateService;

@SpringBootTest(classes = { RatesApplication.class })
@RunWith(SpringRunner.class)
public class CurrencyRateServiceImplTest {

	@Autowired
	private CurrencyRateService service;
	@Mock
	private CurrencyRateService mockcService;
	
	@MockBean
	private CurrencyRateRepository repository;
	List<ExchangeRateRes> exchangeRates = new ArrayList<ExchangeRateRes>();
	CurrencyExchangeEntity mockEntity = new CurrencyExchangeEntity();

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void initRate() throws ParseException {
		mockEntity.setDate(formatter.parse("2020-04-01"));
		mockEntity.setBaseCurrency("EUR");
		// mockEntity.setId(1l);
		mockEntity.setCurrencyValue(82.098f);
		mockEntity.setTargetCurrency("INR");
	}

	@Test
	public void testSaveEntity() {
		CurrencyExchangeEntity mockEntity1 = mock(CurrencyExchangeEntity.class);
		doNothing().when(mockcService).save(isA(CurrencyExchangeEntity.class));
		mockEntity1.setCurrencyValue(82.098f);
		verify(mockEntity1, times(1)).setCurrencyValue(82.098f);
		

	}

	@Test
	public void testgetDailyRate() throws ParseException {
		when(repository.findAllByDate(any(Date.class))).thenReturn(Collections.singletonList(mockEntity));
		assertEquals(getExchangeEntity(), service.getDailyRate(formatter.parse("2020-04-01")));
	}

	@Test
	public void testgetMonthlyRate() throws ParseException {
		when(repository.findAllByDateBetween(any(Date.class), any(Date.class)))
				.thenReturn(Collections.singletonList(mockEntity));
		assertEquals(getExchangeEntity(),
				service.getMonthlyRate(formatter.parse("2020-04-01"), formatter.parse("2020-04-31")));
	}

	private List<CurrencyExchangeEntity> getExchangeEntity() throws ParseException {
		List<CurrencyExchangeEntity> rates = new ArrayList<>();
		CurrencyExchangeEntity expectedRate1 = new CurrencyExchangeEntity();

		expectedRate1.setDate(formatter.parse("2020-04-01"));
		// expectedRate1.setId(1l);
		expectedRate1.setCurrencyValue(82.098f);
		expectedRate1.setBaseCurrency("EUR");
		expectedRate1.setTargetCurrency("INR");
		rates.add(expectedRate1);

		return rates;
	}
}
