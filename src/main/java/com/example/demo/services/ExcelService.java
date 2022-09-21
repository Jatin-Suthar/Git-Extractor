package com.example.demo.services;

import java.io.File;

public interface ExcelService {
	
	/**
	 * this service operation when called, will add row in excel sheet...
	 * 
	 * @param repoName
	 * @param extensionName
	 * @param fileCount
	 * @param rowCount
	 */
	public void addRow(String repoName, String extensionName, int fileCount, int rowCount);
	
	/**
	 * this service operation when called, will create final excel file and returns it to RepoService...
	 * 
	 * @param userName
	 * @return
	 */
	public File createFinalExcel(String userName);
}
