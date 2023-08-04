package com.vtalent.service.impl;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vtalent.Reponse.SearchResponseClass;
import com.vtalent.Repository.EligibilityDetailsRepository;
import com.vtalent.entity.EligibiletyDetails;
import com.vtalent.request.SearchRequestClass;
import com.vtalent.service.EligibiligyService;

@Service
public class EligibilityServiceImpl implements EligibiligyService {

	@Autowired
	private EligibilityDetailsRepository eligibilityDetailsRepository;

	@Override
	public List<String> getUniquePlanNames() {
		// TODO Auto-generated method stub

		List<String> findPlanNames = eligibilityDetailsRepository.findPlanNames();

		return findPlanNames;
	}

	@Override
	public List<String> getUniquePlanStatus() {
		// TODO Auto-generated method stub

		List<String> findPlanStatus = eligibilityDetailsRepository.findPlanStatus();

		return findPlanStatus;
	}

	@Override
	public List<SearchResponseClass> searching(SearchRequestClass searchRequest) {
		// TODO Auto-generated method stub
		List<SearchResponseClass> responseList = new ArrayList<>();

		// create object to eligibiletyDetails class nothing entity class
		// to perform query by Example
		EligibiletyDetails queryBuilder = new EligibiletyDetails();

		// get the plan name from RequestClass
		String planName = searchRequest.getPlanName();
		// and perform query by example logic...
		// planName not null and empty
		if (planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}

		// get the planStatus form RequestClass
		String planStatus = searchRequest.getPlanStatus();
		// perform query by example logic...
		// planName not null and empty
		if (planStatus != null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}

		// get the planStartDate From RequestClass
		LocalDate planStartDate = searchRequest.getPlanStartDate();
		// if planStartDate date
		if (planStartDate != null) {
			queryBuilder.setPlanStartDate(planStartDate);
		}

		// get the planEntDate from requestClass
		LocalDate planEndDate = searchRequest.getPlanEndDate();
		if (planEndDate != null) {
			queryBuilder.setPlanEndDate(planEndDate);
		}

		// create queryByExample to above logics
		Example<EligibiletyDetails> example = Example.of(queryBuilder);

		List<EligibiletyDetails> entities = eligibilityDetailsRepository.findAll(example);

		for (EligibiletyDetails entity : entities) {
			SearchResponseClass src = new SearchResponseClass();

			BeanUtils.copyProperties(entity, src);
			responseList.add(src);
		}
		return responseList;
	}

	@Override
	public void generateExcelSheet(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<EligibiletyDetails> entities = eligibilityDetailsRepository.findAll();

		HSSFWorkbook workBook = new HSSFWorkbook();

		HSSFSheet sheet = workBook.createSheet();

		HSSFRow headRow = sheet.createRow(0);

		headRow.createCell(0).setCellValue("Name");
		headRow.createCell(1).setCellValue("Mobile");
		headRow.createCell(2).setCellValue("Gender");
		headRow.createCell(3).setCellValue("Ssn");

		int i = 1;
		for (EligibiletyDetails entity : entities) {

			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getPlanName());
			dataRow.createCell(1).setCellValue(entity.getMobile());
			dataRow.createCell(2).setCellValue(String.valueOf(entity.getGender()));
			dataRow.createCell(3).setCellValue(entity.getSsn());

			i++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
 
	}

	@Override
	public void generatePdfDocument(HttpServletResponse response) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		List<EligibiletyDetails> entites = eligibilityDetailsRepository.findAll();

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Search Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("phoneNumber", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Ssn", font));
		table.addCell(cell);

		for (EligibiletyDetails entity : entites) {
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobile()));
			table.addCell(String.valueOf(entity.getGender()));
			table.addCell(String.valueOf(entity.getSsn()));
		}
		document.add(table);
		document.close();

	}

}
