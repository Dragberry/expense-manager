package net.dragberry.expman.dao;

import java.math.BigDecimal;

import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.query.InterchangeTypeListQuery;
import net.dragberry.expman.result.ResultList;

public interface InterchangeDao {
	
	Interchange createInterchange(Interchange interchange);
	
	InterchangeType createInterchangeType(InterchangeType interchangeType);
	
	/**
	 * Fetches the list of {@link Interchange} based on {@link InterchangeListQuery}
	 * 
	 * @param interchangeTypeListQuery  {@link InterchangeListQuery}
	 * @return {@link ResultList} of {@link Interchange}
	 */
	ResultList<Interchange> fetchInterchangeList(InterchangeListQuery query);
	
	/**
	 * Fetches the list of {@link InterchangeType} based on {@link InterchangeTypeListQuery}
	 * 
	 * @param interchangeTypeListQuery  {@link InterchangeTypeListQuery}
	 * @return {@link ResultList} of {@link InterchangeType}
	 */
	ResultList<InterchangeType> fetchInterchangeTypeList(InterchangeTypeListQuery interchangeTypeListQuery);

	/**
	 * Calculate current balance for customer
	 * 
	 * @param customerKey
	 * @return
	 */
	BigDecimal getRealTimeBalance(Long customerKey, String currency);
}
