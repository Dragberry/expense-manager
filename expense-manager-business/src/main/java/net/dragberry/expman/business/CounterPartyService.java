package net.dragberry.expman.business;

import javax.ejb.Remote;

import net.dragberry.expman.domain.CounterParty;

@Remote
public interface CounterPartyService {

	CounterParty createCounterParty(CounterParty counterParty);
}
