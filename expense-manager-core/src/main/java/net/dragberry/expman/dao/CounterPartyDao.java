package net.dragberry.expman.dao;

import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.query.CounterPartyListQuery;
import net.dragberry.expman.result.ResultList;

public interface CounterPartyDao {

	CounterParty createCounterParty(CounterParty counterParty);
	
	ResultList<CounterParty> fetchCounterPartyList(CounterPartyListQuery counterPartyListQuery);
}
