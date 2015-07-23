package net.dragberry.expman.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.dragberry.expman.business.utils.CurrencyUtils;
import net.dragberry.expman.dao.InterchangeDao;
import net.dragberry.expman.domain.Currency;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.query.InterchangeTypeListQuery;
import net.dragberry.expman.result.ResultList;

@Stateless
public class InterchangeServiceBean implements InterchangeService {
	
	@Inject
	private InterchangeDao interchangeDao;

	@Override
	public InterchangeType createInterchangeType(InterchangeType interchangeType) {
		return interchangeDao.createInterchangeType(interchangeType);
	}

	@Override
	public Interchange createInterchange(Interchange interchange) {
		return interchangeDao.createInterchange(interchange);
	}

	@Override
	public ResultList<Interchange> fetchInterchangeList(InterchangeListQuery interchangeListQuery) {
		return interchangeDao.fetchInterchangeList(interchangeListQuery);
	}

	@Override
	public ResultList<InterchangeType> fetchInterchangeTypeList(InterchangeTypeListQuery interchangeTypeListQuery) {
		return interchangeDao.fetchInterchangeTypeList(interchangeTypeListQuery);
	}

	@Override
	public BigDecimal getRealTimeBalance(Long customerKey, String currency) throws BusinessException {
		if (customerKey == null) {
			throw new BusinessException("The customer is not define!");
		}
		if (currency == null) {
			throw new BusinessException("The currency is not define!");
		}
		return interchangeDao.getRealTimeBalance(customerKey, currency);
	}
	
	@Override
	public Map<Currency, BigDecimal> getAllRealTimeBalances(Long customerKey) throws BusinessException {
		if (customerKey == null) {
			throw new BusinessException("The customer is not define!");
		}
		Map<Currency, BigDecimal> balanceMap = new HashMap<Currency, BigDecimal>();
		for (Currency currency : CurrencyUtils.getCurrencyList()) {
			BigDecimal balance = interchangeDao.getRealTimeBalance(customerKey, currency.getCurrencyCode());
			if (balance != null) {
				balanceMap.put(currency, balance);
			}
		}
		return balanceMap;
	}
	
	@Override
	public Map<InterchangeType, Map<Currency, BigDecimal>> calculateExpenses(Long customerKey, List<InterchangeType> typeList) {
		return calculateEarningsOrExpenses(TransactionType.DEBIT, customerKey, typeList);
	}
	
	@Override
	public Map<InterchangeType, Map<Currency, BigDecimal>> calculateEarnings(Long customerKey, List<InterchangeType> typeList) {
		return calculateEarningsOrExpenses(TransactionType.CREDIT, customerKey, typeList);
	}
	
	private Map<InterchangeType, Map<Currency, BigDecimal>> calculateEarningsOrExpenses(TransactionType transactionType, Long customerKey, List<InterchangeType> typeList) {
		Map<InterchangeType, Map<Currency, BigDecimal>> result = new LinkedHashMap<InterchangeType, Map<Currency, BigDecimal>>();
		for (InterchangeType type : typeList) {
			Map<Currency, BigDecimal> expenseMap = new HashMap<Currency, BigDecimal>();
			for (Currency currency : CurrencyUtils.getCurrencyList()) {
				BigDecimal balance = interchangeDao.calculateExpense(transactionType, customerKey, currency.getCurrencyCode(), type.getInterchangeTypeKey());
				if (balance != null) {
					expenseMap.put(currency, balance);
				}
			}
			result.put(type, expenseMap);
		}
		return result;
	}

}
