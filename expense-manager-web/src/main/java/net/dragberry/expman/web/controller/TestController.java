package net.dragberry.expman.web.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import net.dragberry.expman.business.InterchangeService;
import net.dragberry.expman.web.util.BeanLocator;

@Named("testController")
@RequestScoped
public class TestController {
	
	private String testString = "Test String";

	public String getTestString() {
		try {
			InterchangeService interchangeService = BeanLocator.getInterchangeService();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
	
}
