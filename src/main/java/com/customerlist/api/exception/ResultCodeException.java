package com.customerlist.api.exception;

public class ResultCodeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ResultCode resultCode;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public ResultCodeException setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
		return this;
	}
	
	
}
