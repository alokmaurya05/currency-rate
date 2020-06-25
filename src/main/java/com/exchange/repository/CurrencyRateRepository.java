package com.exchange.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exchange.entity.CurrencyExchangeEntity;

/*This class use get daily & monthly Rates  */
@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyExchangeEntity, UUID> {

	public List<CurrencyExchangeEntity> findAllByDate(@Param("date") Date date);

	public List<CurrencyExchangeEntity> findAllByDateBetween(Date startDate, Date endDate);

}
