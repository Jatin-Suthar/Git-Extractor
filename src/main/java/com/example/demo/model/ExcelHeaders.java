package com.example.demo.model;

public enum ExcelHeaders {
	REPOSITORY("Repository"), LANGUAGE("Language"), FILE_COUNT("File Count"); 
	String value;
	ExcelHeaders(String value) {
		this.value=value;
	}
}
