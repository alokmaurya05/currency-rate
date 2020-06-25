package com.exchange.validation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import com.exchange.error.InvalidCurrencyException;
import com.exchange.error.InvalidDateFormateException;
import com.exchange.error.InvalidDateIntervalException;
import com.exchange.error.NoDataOnSatSunException;

/**
 * This Class is use to validate input user data
 */
public class ExchangeRateValidation {

	/**
	 * This method checks whether the given base & target currency is valid
	 * 
	 * @param Str String to check
	 * @return TRUE if it is valid input
	 */
	public static boolean isVaildCurrency(String str) {
		if ((str.length() > 3) || !(str.matches("^[a-zA-Z]*$"))) {
			throw new InvalidCurrencyException(str);
		}
		return (str.length() == 3);
	}

	/**
	 * This method checks whether the given date is Valid
	 * 
	 * @param dateStr String to check, cannot be null
	 * @return TRUE if date is Valid
	 */
	public static boolean isValidDate(String dateStr) {
		boolean validDate = false;
		try {
			LocalDate date = LocalDate.parse(dateStr);
			validDate = (LocalDate.parse("2000-01-01").isBefore(date) && LocalDate.now().isAfter(date));
			if (!validDate) {
				throw new InvalidDateIntervalException(dateStr);
			}
			if (isWeekend(date)) {
				throw new NoDataOnSatSunException(dateStr);
			}
		} catch (DateTimeParseException e) {
			throw new InvalidDateFormateException(dateStr);
		}
		return validDate;
	}

	/**
	 * This method checks whether the given date is Valid month
	 * 
	 * @param dateStr String to check, cannot be null
	 * @return TRUE if date is Valid
	 */
	public static boolean isValidMonth(String dateStr) {
		boolean validDate = false;
		try {
			LocalDate date = LocalDate.parse(dateStr);
			validDate = (LocalDate.parse("2000-01-01").isBefore(date) && LocalDate.now().isAfter(date));
			if (!validDate) {
				throw new InvalidDateIntervalException(dateStr);
			}

		} catch (DateTimeParseException e) {
			throw new InvalidDateFormateException(dateStr);
		}
		return validDate;
	}

	/**
	 * This method checks whether the given date is the weekend (Saturday or Sunday)
	 * 
	 * @param date Date to check, cannot be null
	 * @return TRUE if date is Saturday or Sunday
	 */
	private static boolean isWeekend(LocalDate date) {
		DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		switch (dayOfWeek) {
		case SATURDAY:
		case SUNDAY:
			return true;
		default:
			return false;
		}
	}

}
