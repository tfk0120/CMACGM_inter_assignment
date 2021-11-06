package com.customerlist.api.exception;

import org.springframework.http.HttpStatus;

public class ResultCode {

	int code;
	String description;
	HttpStatus mappedHttpStatus;
	
	public static final ResultCode SUCCESS = new ResultCode(0, "Success").setMappedHttpStatus(HttpStatus.OK);

	public static final ResultCode SERVER_ERROR = new ResultCode(-1, "Server Error").setMappedHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

//	public static final ResultCode UNKNOWN_SERVICE = new ResultCode(-2, "Unknown Service");

	public static final ResultCode AUTHENTICATION_ERROR = new ResultCode(-2, "Authentication Error").setMappedHttpStatus(HttpStatus.UNAUTHORIZED);

	public static final ResultCode INPUT_MISSING = new ResultCode(-3, "Input Missing").setMappedHttpStatus(HttpStatus.BAD_REQUEST);
	
	public static final ResultCode RESOURCE_NOT_FOUND = new ResultCode(-4, "Resource Not Found").setMappedHttpStatus(HttpStatus.NOT_FOUND);

	public ResultCode(int code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public HttpStatus getMappedHttpStatus() {
		return mappedHttpStatus;
	}

	public ResultCode setMappedHttpStatus(HttpStatus mappedHttpStatus) {
		this.mappedHttpStatus = mappedHttpStatus;
		return this;
	}
	
	public ResultCodeException exception() {
		return new ResultCodeException().setResultCode(this);
	}


}
