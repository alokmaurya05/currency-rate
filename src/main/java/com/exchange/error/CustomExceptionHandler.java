package com.exchange.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*This class is use to send exceptional information */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(InvalidDateFormateException.class)
    public ResponseEntity<CustomErrorResponse> dateHandler(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setTitle("Input Date issue");
        errors.setDescription(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
	
	
	@ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<CustomErrorResponse> currencyHandler(Exception ex, WebRequest request) 
	{

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setTitle("Input currency issue");
        errors.setDescription(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
	
	@ExceptionHandler(InvalidDateIntervalException.class)
    public ResponseEntity<CustomErrorResponse> dateInBetweenHandler(Exception ex, WebRequest request) 
	{

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setTitle("Input Date issue");
        errors.setDescription(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
	
	@ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<CustomErrorResponse> noDataFoundHandler(Exception ex, WebRequest request) 
	{

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setTitle("Data Not Found");
        errors.setDescription(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
	
	@ExceptionHandler(NoDataOnSatSunException.class)
    public ResponseEntity<CustomErrorResponse> noDataFoundOnSunSatHandler(Exception ex, WebRequest request) 
	{

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setTitle("Data Not Found");
        errors.setDescription(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handler(Exception ex, WebRequest request) 
	{

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setTitle("Server Error");
        errors.setDescription("Not able to process request");
        errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
