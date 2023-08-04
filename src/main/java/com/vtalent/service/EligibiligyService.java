package com.vtalent.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.vtalent.Reponse.SearchResponseClass;
import com.vtalent.request.SearchRequestClass;

public interface EligibiligyService {
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
	
	public List<SearchResponseClass> searching(SearchRequestClass request);
	
	public void generateExcelSheet(HttpServletResponse response) throws IOException;
	
	public void generatePdfDocument(HttpServletResponse response) throws DocumentException, IOException;
	
	
	

	
	

}
