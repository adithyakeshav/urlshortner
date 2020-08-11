package com.keshava.shorten.exceptionhandler;

public class ErrorResponse {
    private String errorMessage;
    private String requestedUrl;

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    public ErrorResponse() {}

	public String getRequestedUrl() {
		return requestedUrl;
	}

	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}

	public ErrorResponse(String errorMessage, String requestedUrl) {
		this.errorMessage = errorMessage;
		this.requestedUrl = requestedUrl;
	}
}