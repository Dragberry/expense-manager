package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.result.ResultList;

public interface InterchangeDao {
	
	Interchange createInterchange(Interchange interchange);
	
	InterchangeType createInterchangeType(InterchangeType interchangeType);
	
	ResultList<Interchange> fetchInterchangeList(InterchangeListQuery query);

}
