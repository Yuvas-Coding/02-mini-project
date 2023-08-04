package com.vtalent.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtalent.service.EligibiligyService;

@RestController
public class RestControllerClass {

	@Autowired
	private EligibiligyService eligibiligyService;

	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames() {

		List<String> uniquePlanNames = eligibiligyService.getUniquePlanNames();

		return new ResponseEntity<>(uniquePlanNames, HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<List<String>> getPlanStatus() {

		List<String> uniquePlanStatus = eligibiligyService.getUniquePlanStatus();

		return new ResponseEntity<>(uniquePlanStatus, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.xls";
		
		response.setHeader(headerKey, headerValue);
		
		eligibiligyService.generateExcelSheet(response);
	}
	
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/pdf");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.pdf";
		
		response.setHeader(headerKey, headerValue);
		
		eligibiligyService.generatePdfDocument(response);
	}
	
	
	

}
