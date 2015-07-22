package net.dragberry.expman.dao;

import net.dragberry.expman.domain.CounterParty;

public class CounterPartyDaoImpl extends AbstractDao implements CounterPartyDao {

	@Override
	public CounterParty createCounterParty(CounterParty counterParty) {
		getEntityManager().persist(counterParty);
		return counterParty;
	}

}
