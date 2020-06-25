package com.exchange.servicesImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.exchange.error.InvalidDateFormateException;
import com.exchange.error.NoDataFoundException;
import com.exchange.error.NoDataOnSatSunException;
import com.exchange.model.ExchangeRateReq;
import com.exchange.model.ExchangeRateRes;
import com.exchange.rates.RatesApplication;
import com.exchange.services.CurrencyExchangeService;

@SpringBootTest(classes = { RatesApplication.class })
@RunWith(SpringRunner.class)
public class CurrencyExchangeServiceImplTest {

	@Autowired
	private CurrencyExchangeService service;
	@MockBean
	private CurrencyExchangeService mockcService;
	ExchangeRateRes mockRate1 = null;

	@Before
	public void initRate() {
		mockRate1 = new ExchangeRateRes(LocalDate.parse("2020-04-01"), 82.234f, 81.98f, "Decending");
	}

	@Test
	public void testGetRateByDateBaseAndTarget() {

		when(mockcService.getRate(any(ExchangeRateReq.class))).thenReturn(Collections.singletonList(mockRate1));

		ExchangeRateReq exchangeRateReq = new ExchangeRateReq("2020-04-01", "EUR", "INR");
		assertEquals(getExchangeRate(), service.getRate(exchangeRateReq));
	}

	@Test(expected = InvalidDateFormateException.class)
	public void testGetInvalidDateFormateException() {
		when(mockcService.getRate(any(ExchangeRateReq.class))).thenThrow(new InvalidDateFormateException("2020-15-01"));
		ExchangeRateReq exchangeRateReq = new ExchangeRateReq("2020-15-01", "EUR", "INR");
		service.getRate(exchangeRateReq);
	}

	@Test(expected = NoDataFoundException.class)
	public void testGetRateNoDataFoundException() {
		when(mockcService.getRate(any(ExchangeRateReq.class))).thenThrow(new NoDataFoundException("2020-04-05"));

		ExchangeRateReq exchangeRateReq = new ExchangeRateReq("2020-04-05", "EUR", "INR");
		service.getRate(exchangeRateReq);
	}

	@Test(expected = NoDataOnSatSunException.class)
	public void testGetNoDataOnSatSunException() {
		when(mockcService.getRate(any(ExchangeRateReq.class))).thenThrow(new NoDataOnSatSunException("2020-05-04"));

		ExchangeRateReq exchangeRateReq = new ExchangeRateReq("2020-05-04", "EUR", "INR");
		service.getRate(exchangeRateReq);
	}

	private List<ExchangeRateRes> getExchangeRate() {
		List<ExchangeRateRes> rates = new ArrayList<>();
		ExchangeRateRes expectedRate1 = new ExchangeRateRes(LocalDate.parse("2020-04-01"), 82.234f, 81.98f,
				"Decending");
		rates.add(expectedRate1);

		return rates;
	}
}
