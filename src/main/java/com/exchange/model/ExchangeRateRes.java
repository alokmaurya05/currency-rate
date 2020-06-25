package com.exchange.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*This class use to response generation  */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRes {

	private LocalDate date;
	private Float rate;
	private float average;
	private String trend;

}
