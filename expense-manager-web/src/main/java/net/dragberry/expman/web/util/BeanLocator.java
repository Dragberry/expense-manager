package net.dragberry.expman.web.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import net.dragberry.expman.business.InterchangeService;
import net.dragberry.expman.business.InterchangeServiceBean;

public class BeanLocator {
	
	private static Context context;

	private static InterchangeService interchangeService;
	
	public static InterchangeService getInterchangeService() throws NamingException {
		if (interchangeService == null) {
			getContext().lookup(getBeanName("expense-manager-ear", "expense-manager-business", StringUtils.EMPTY, "InterchangeServiceBean", InterchangeService.class));
		}
		return interchangeService;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Context getContext() throws NamingException {
		if (context == null) {
			Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			context = new InitialContext(jndiProperties);
		}
		return context;
	}
	
	private static String getBeanName(String appName, String moduleName, String distinctName, String bean, Class<?> remote) {
		StringBuilder sb = new StringBuilder();
		sb.append("ejb:");
		sb.append(appName);
		sb.append("/");
		sb.append(moduleName);
		sb.append("/");
		sb.append(distinctName);
		sb.append("/");
		sb.append(bean);
		sb.append("!");
		sb.append(remote.getName());
		return sb.toString();
	}
}
