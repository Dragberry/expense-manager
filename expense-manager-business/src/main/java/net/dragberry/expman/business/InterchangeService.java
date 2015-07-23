package net.dragberry.expman.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import net.dragberry.expman.domain.Currency;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.query.InterchangeTypeListQuery;
import net.dragberry.expman.result.ResultList;

@Remote
public interface InterchangeService {

	InterchangeType createInterchangeType(InterchangeType interchangeType);
	
	Interchange createInterchange(Interchange interchange);
	
	ResultList<Interchange> fetchInterchangeList(InterchangeListQuery interchangeListQuery);

	ResultList<InterchangeType> fetchInterchangeTypeList(InterchangeTypeListQuery interchangeTypeListQuery);

	BigDecimal getRealTimeBalance(Long customerKey, String currency) throws BusinessException;

	Map<Currency, BigDecimal> getAllRealTimeBalances(Long customerKey) throws BusinessException;

	Map<InterchangeType, Map<Currency, BigDecimal>> calculateExpenses(Long customerKey, List<InterchangeType> typeList);

	Map<InterchangeType, Map<Currency, BigDecimal>> calculateEarnings(Long customerKey, List<InterchangeType> typeList);

}
