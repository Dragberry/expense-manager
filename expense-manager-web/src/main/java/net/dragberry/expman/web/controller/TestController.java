package net.dragberry.expman.web.controller;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import net.dragberry.expman.business.BusinessException;
import net.dragberry.expman.business.InterchangeService;
import net.dragberry.expman.business.utils.CurrencyUtils;
import net.dragberry.expman.web.util.BeanLocator;

@Named("testController")
@RequestScoped
public class TestController {
	
	private String testString = "Test String";
	
	private InterchangeService interchangeService = null;

	public String getTestString() throws BusinessException {
		try {
			interchangeService = BeanLocator.getInterchangeService();
			BigDecimal balance = interchangeService.getRealTimeBalance(1L, "BYR");
			System.out.println(balance);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
	
}
