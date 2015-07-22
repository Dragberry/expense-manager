package net.dragberry.expman.dao;

public enum TransactionType {

	DEBIT("D"), CREDIT("C");
	
	private String type;
	
	private TransactionType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return getType();
	}

	public String getType() {
		return type;
	}
	
}
