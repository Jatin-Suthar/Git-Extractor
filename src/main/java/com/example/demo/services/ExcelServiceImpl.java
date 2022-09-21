package com.example.demo.services;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.model.ExcelHeaders;

@Service
public class ExcelServiceImpl implements ExcelService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	public ExcelServiceImpl() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("sheet1");
		Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue(ExcelHeaders.REPOSITORY.toString());
	    header.createCell(1).setCellValue(ExcelHeaders.LANGUAGE.toString());
	    header.createCell(2).setCellValue(ExcelHeaders.FILE_COUNT.toString());
	}
	
	@Override
	public void addRow(String repoName, String extensionName, int fileCount, int rowCount) {
		XSSFRow row = sheet.createRow(rowCount);
		row.createCell(0).setCellValue((String) repoName);
		row.createCell(1).setCellValue(String.valueOf(extensionName));
		row.createCell(2).setCellValue(String.valueOf(fileCount));
	}
	
	@Override
	public File createFinalExcel(String userName) {
		try {
			File newFile=new File("./"+userName+".xlsx");
			FileOutputStream file = new FileOutputStream(newFile);
			workbook.write(file);
			return newFile;
		} catch(Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
}
