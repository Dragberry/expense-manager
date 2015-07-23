package net.dragberry.expman.business;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = -3043889734812851679L;

	private String errorMessage;

	public BusinessException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
