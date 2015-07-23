package net.dragberry.expman.domain;

import java.io.Serializable;

public class Currency implements Serializable {
	
	private static final long serialVersionUID = 2120122036286514810L;

	private String currencyCode;
	
	private String currencyName;
	
	private String countryCode;
	
	 public Currency(String code, String name, String country) {
		 this.countryCode = country;
		 this.currencyCode = code;
		 this.currencyName = name;
	 }
	 
	 public Currency(String code, String country) {
		 this(code, null, country);
	 }

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return this.currencyCode;
	}
}
