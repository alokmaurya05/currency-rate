package com.exchange.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.buildResponse.Response;
import com.exchange.entity.CurrencyExchangeEntity;
import com.exchange.error.NoDataFoundException;
import com.exchange.helper.ExchangeHelper;
import com.exchange.model.ExchangeRateReq;
import com.exchange.model.ExchangeRateRes;

import com.exchange.services.CurrencyExchangeService;
import com.exchange.services.CurrencyRateService;
import com.exchange.validation.ExchangeRateValidation;

import lombok.NonNull;

/*This class process all the request
 * And garnered response  */
@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateResource {
	public static final Logger logger = LoggerFactory.getLogger(ExchangeRateResource.class);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private CurrencyExchangeService exchangeService;

	@Autowired
	private CurrencyRateService currencyRateService;

	/**
	 * This method give rate on given date, base & target currency
	 * 
	 * @param date           String
	 * @param baseCurrency   String
	 * @param targetCurrency String
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/{date}/{baseCurrency}/{targetCurrency}")
	public ResponseEntity<?> getRate(@PathVariable("date") @NonNull String date,
			@PathVariable("baseCurrency") @NonNull String baseCurrency,
			@PathVariable("targetCurrency") @NonNull String targetCurrency) throws ParseException {
		ExchangeRateRes rateResponse = null;
		if (ExchangeRateValidation.isValidDate(date) && ExchangeRateValidation.isVaildCurrency(baseCurrency)
				&& ExchangeRateValidation.isVaildCurrency(targetCurrency)) {
			ExchangeRateReq exchangeRateRequest = new ExchangeRateReq(date, baseCurrency, targetCurrency);

			List<ExchangeRateRes> rateList = exchangeService.getRate(exchangeRateRequest);

			if (rateList.isEmpty()) {
				logger.error("No currency rate with date {} not found.", date);
				throw new NoDataFoundException(
						date.toString() + "/" + baseCurrency + "/" + targetCurrency + " not found");
			}
			List<ExchangeRateRes> sortedList = ExchangeHelper.sortedByDate(rateList);
			rateResponse = new ExchangeRateRes(LocalDate.parse(date), sortedList.get(0).getRate(),
					ExchangeHelper.averageRate(sortedList), ExchangeHelper.trendRate(sortedList));

			CurrencyExchangeEntity e = new CurrencyExchangeEntity(UUID.randomUUID(), formatter.parse(date),
					rateResponse.getRate(), baseCurrency, targetCurrency);
			currencyRateService.save(e);
		}
		return Response.success(rateResponse, HttpStatus.OK);
	}

	/**
	 * This method give rates on given valid date
	 * 
	 * @param year  String
	 * @param month String
	 * @param day   String
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/history/daily/{year}/{month}/{day}")
	public ResponseEntity<?> getDailyRate(@PathVariable("year") String year, @PathVariable("month") String month,
			@PathVariable("day") String day) throws ParseException {
		List<CurrencyExchangeEntity> rateList = new ArrayList<CurrencyExchangeEntity>();
		String date = year + "-" + month + "-" + day;

		if (ExchangeRateValidation.isValidDate(date)) {
			rateList = currencyRateService.getDailyRate(formatter.parse(date));
			if (rateList.isEmpty()) {
				logger.error("No data on date {} not found.", date);
				throw new NoDataFoundException("On " + date + " Not found");
			}

		}
		return Response.success(rateList, HttpStatus.OK);
	}

	/**
	 * This method give rates on given valid month
	 * 
	 * @param year  String
	 * @param month String
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/history/monthly/{year}/{month}")
	public ResponseEntity<?> getMonthlyRate(@PathVariable("year") String year, @PathVariable("month") String month)
			throws ParseException {
		List<CurrencyExchangeEntity> rateList = new ArrayList<CurrencyExchangeEntity>();
		String sartDate = year + "-" + month + "-" + "01";
		String endDate = year + "-" + month + "-" + "31";

		if (ExchangeRateValidation.isValidMonth(sartDate)) {
			rateList = currencyRateService.getMonthlyRate(formatter.parse(sartDate), formatter.parse(endDate));
			if (rateList.isEmpty()) {
				logger.error("No data inbetween sartDate {}  and endDate {}.", sartDate, endDate);
				throw new NoDataFoundException("found" + " in between " + sartDate + "  " + endDate);
			}

		}
		return Response.success(rateList, HttpStatus.OK);
	}

}
