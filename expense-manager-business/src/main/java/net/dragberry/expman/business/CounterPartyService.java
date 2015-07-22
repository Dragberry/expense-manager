package net.dragberry.expman.business;

import javax.ejb.Remote;

import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.query.CounterPartyListQuery;
import net.dragberry.expman.result.ResultList;

@Remote
public interface CounterPartyService {

	CounterParty createCounterParty(CounterParty counterParty);
	
	ResultList<CounterParty> fetchCounterPartyList(CounterPartyListQuery counterPartyListQuery);
}
