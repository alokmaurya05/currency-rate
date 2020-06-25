package com.exchange.resource;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exchange.entity.CurrencyExchangeEntity;
import com.exchange.model.ExchangeRateReq;
import com.exchange.model.ExchangeRateRes;
import com.exchange.rates.RatesApplication;
import com.exchange.resources.ExchangeRateResource;
import com.exchange.services.CurrencyExchangeService;
import com.exchange.services.CurrencyRateService;


@SpringBootTest(classes={RatesApplication.class})
@RunWith(SpringRunner.class)
/*This class for resource test
 * We can add more test case also. This is just a basic.
 * */
public class ExchangeRateResourceTest 
{
	List<ExchangeRateRes> rateList=new ArrayList<ExchangeRateRes>();	
	MockMvc mockMvc;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	ExchangeRateResource rateResource;
    
	@MockBean
	CurrencyExchangeService currencyExchangeService;
	@MockBean
	CurrencyRateService currencyRateService;
	
	@Before
	public void setup()
	{
		 this.mockMvc = standaloneSetup(this.rateResource).build();// Standalone context
	}
	
	@Test
	public void testGetRate() throws Exception 
	{
		   when(currencyExchangeService.getRate(any(ExchangeRateReq.class))).thenReturn(dataBuild());
		
			mockMvc.perform(get("/api/exchange-rate/2020-04-01/EUR/INR").contentType(MediaType.APPLICATION_JSON))
			 .andExpect(status().isOk())
			 .andExpect(jsonPath("$.date[0]", is(2020)))
			 .andExpect(jsonPath("$.date[1]", is(4)))
			 .andExpect(jsonPath("$.date[2]", is(1)))
			 .andExpect(jsonPath("$.rate", is(84.235)))
			 .andExpect(jsonPath("$.average", is(83.2278)))
			 .andExpect(jsonPath("$.trend", is("ASCENDING"))) ;
	} 
	
	@Test(expected = Exception.class)
	public void testGetRateException1() throws Exception  
	{
	        mockMvc.perform(get("/api/exchange-rate/2020-14-01/EUR/INR").contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isBadRequest())
				   .andExpect(jsonPath("$.title", is("Input Date issue")))
				   .andExpect(jsonPath("$.description", is("Invalid Date Formate, should be Like 2020-04-01 Not "+"2020-14-01")));
		
	} 
	
	@Test(expected = Exception.class)
	public void testGetRateException2() throws Exception  
	{
	        mockMvc.perform(get("/api/exchange-rate/2020-04-01/E4R/INR").contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isBadRequest())
				   .andExpect(jsonPath("$.title", is("Input currency issue")))
				   .andExpect(jsonPath("$.description", is("Invalid Currency Enter, should be like: EUR/INR  Not "+"E4R")));
		
	} 
	
	
	@Test
	public void testGetDailyRate() throws Exception 
	{
		   when(currencyRateService.getDailyRate(any(Date.class))).thenReturn(dataCurrencyExchangeBuild());
		
			mockMvc.perform(get("/api/exchange-rate/history/daily/2020/04/01").contentType(MediaType.APPLICATION_JSON))
			 .andExpect(status().isOk())
			 .andExpect(jsonPath("$[0].date", is(1585679400000L)))
			 .andExpect(jsonPath("$[0].currencyValue", is(82.234)))
			 .andExpect(jsonPath("$[0].baseCurrency", is("EUR")))
			 .andExpect(jsonPath("$[0].targetCurrency", is("INR"))) ;
	} 
	
	@Test
	public void testGetMonthlyRate() throws Exception 
	{
		   when(currencyRateService.getMonthlyRate(any(Date.class),any(Date.class))).thenReturn(dataCurrencyExchangeBuild());
		
			mockMvc.perform(get("/api/exchange-rate/history/monthly/2020/04").contentType(MediaType.APPLICATION_JSON))
			 .andExpect(status().isOk())
			 .andExpect(jsonPath("$[0].date", is(1585679400000L)))
			 .andExpect(jsonPath("$[0].currencyValue", is(82.234)))
			 .andExpect(jsonPath("$[0].baseCurrency", is("EUR")))
			 .andExpect(jsonPath("$[0].targetCurrency", is("INR"))) ;
	} 
	

	private  List<ExchangeRateRes> dataBuild()
	{
		 ExchangeRateRes exchRate1=new ExchangeRateRes();
		 ExchangeRateRes exchRate2=new ExchangeRateRes();
		 ExchangeRateRes exchRate3=new ExchangeRateRes();
		 ExchangeRateRes exchRate4=new ExchangeRateRes();
		 ExchangeRateRes exchRate5=new ExchangeRateRes();
		 exchRate1.setDate(LocalDate.parse("2020-04-01"));
		 exchRate1.setRate(82.102f);
			 
		 exchRate2.setDate(LocalDate.parse("2020-04-02"));
		 exchRate2.setRate(82.234f);
			
		 exchRate3.setDate(LocalDate.parse("2020-04-03"));
		 exchRate3.setRate(83.334f);
				 
		 exchRate4.setDate(LocalDate.parse("2020-04-06"));
		 exchRate4.setRate(84.234f);
			
		 exchRate5.setDate(LocalDate.parse("2020-04-07"));
		 exchRate5.setRate(84.235f);
			
		 rateList.add(exchRate1);
		 rateList.add(exchRate2);
		 rateList.add(exchRate3);
		 rateList.add(exchRate4);
		 rateList.add(exchRate5);
		return rateList;
		
	}
	
	private  List<CurrencyExchangeEntity> dataCurrencyExchangeBuild() throws ParseException 
	{
		List<CurrencyExchangeEntity> exchangeEntity = new ArrayList<CurrencyExchangeEntity>();
		CurrencyExchangeEntity exchEntity=new CurrencyExchangeEntity();
		//exchEntity.setId(1l);
		exchEntity.setDate(formatter.parse("2020-04-01"));
		exchEntity.setCurrencyValue(82.234f);
		exchEntity.setBaseCurrency("EUR");
		exchEntity.setTargetCurrency("INR");
         exchangeEntity.add(exchEntity);
	    return exchangeEntity;
		
	}
	
}

