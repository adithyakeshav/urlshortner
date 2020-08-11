package com.keshava.shorten.exceptionhandler;

public class ErrorResponse {
    private String errorMessage;
    private String generatedUrl;

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getGeneratedUrl() {
		return generatedUrl;
	}
	public void setGeneratedUrl(String generatedUrl) {
		this.generatedUrl = generatedUrl;
    }
    
    public ErrorResponse() {}

	public ErrorResponse(String errorMessage, String generatedUrl) {
		this.errorMessage = errorMessage;
		this.generatedUrl = generatedUrl;
	}
}