package net.dragberry.expman.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.dragberry.expman.business.utils.CurrencyUtils;
import net.dragberry.expman.dao.InterchangeDao;
import net.dragberry.expman.domain.Currency;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
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

}
