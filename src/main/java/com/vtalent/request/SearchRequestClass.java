package com.vtalent.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequestClass {
	
	private String planName;
	
	private String planStatus;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;

}
