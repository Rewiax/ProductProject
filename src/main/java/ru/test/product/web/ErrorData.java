package ru.test.product.web;

public class ErrorData {

	private int errorCode;
	private String errorMessage;	
	
	public ErrorData(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}	
	
}
