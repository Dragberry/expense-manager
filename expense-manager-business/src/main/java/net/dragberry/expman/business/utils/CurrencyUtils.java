package net.dragberry.expman.business.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.dragberry.expman.domain.Currency;

public class CurrencyUtils {
	
	private static final Map<String, Currency> currencyMap = new HashMap<String, Currency>();

	static {
		currencyMap.put("BYR", new Currency("BYR", "BY"));
		currencyMap.put("RUR", new Currency("RUR", "RU"));
		currencyMap.put("USD", new Currency("USD", "US"));
		currencyMap.put("EUR", new Currency("EUR", null));
	}
	
	public static Currency getCurrency(String code) {
		return currencyMap.get(code);
	}
	
	public static Collection<Currency> getCurrencyList() {
		return Collections.unmodifiableCollection(currencyMap.values());
	}
}
