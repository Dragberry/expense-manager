package net.dragberry.expman.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.dragberry.expman.dao.CounterPartyDao;
import net.dragberry.expman.domain.CounterParty;

@Stateless
public class CounterPartyServiceBean implements CounterPartyService {

	@Inject
	private CounterPartyDao counterPartyDao;
	
	@Override
	public CounterParty createCounterParty(CounterParty counterParty) {
		return counterPartyDao.createCounterParty(counterParty);
	}

}
