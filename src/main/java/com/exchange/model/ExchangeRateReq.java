package com.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*This class use to send request  */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateReq {

	private String date;
	private String baseCurrency;
	private String targetCurrency;

}
