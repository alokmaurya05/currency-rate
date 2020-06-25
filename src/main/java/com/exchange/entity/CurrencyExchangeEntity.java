package com.exchange.entity;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;

/*This class use to persist entity after successful of
 * rate found on given Date. */
@Data
@AllArgsConstructor
@Entity
@Table(name="CURRENCY_EXCHANGE")
public class CurrencyExchangeEntity {

	public CurrencyExchangeEntity() {}
	
	@Id
	private UUID id;

	
	@Temporal(TemporalType.DATE)
    private  Date date;
    
	
    private float currencyValue;


    private String baseCurrency;
	
	
    private String targetCurrency;

	
}
