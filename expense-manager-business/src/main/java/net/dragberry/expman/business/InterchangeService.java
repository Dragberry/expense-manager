package net.dragberry.expman.business;

import javax.ejb.Remote;

import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.result.ResultList;

@Remote
public interface InterchangeService {

	InterchangeType createInterchangeType(InterchangeType interchangeType);
	
	Interchange createInterchange(Interchange interchange);
	
	ResultList<Interchange> fetchInterchangeList(InterchangeListQuery interchangeListQuery);
}
