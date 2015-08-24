package net.dragberry.expman.rs.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ExpmanRESTApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	public ExpmanRESTApplication() {
		singletons.add(new TestService());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}

