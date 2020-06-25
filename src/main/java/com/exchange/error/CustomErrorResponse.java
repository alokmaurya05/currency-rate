package com.exchange.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
/*This class use to Build Custom Error response. */
public class CustomErrorResponse 
{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
   
	private LocalDateTime timestamp;
    private int status;
    private String title;
    private String description;
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
