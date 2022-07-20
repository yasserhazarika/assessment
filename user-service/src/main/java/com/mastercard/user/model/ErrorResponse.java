package com.mastercard.user.model;

import java.util.List;

public class ErrorResponse {

	private final String errorCode;

	private final String description;

	private final List<String> details;

	public ErrorResponse(final String errorCode, final String description, final List<String> details) {
		this.errorCode = errorCode;
		this.description = description;
		this.details = details;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getDetails() {
		return details;
	}
}
