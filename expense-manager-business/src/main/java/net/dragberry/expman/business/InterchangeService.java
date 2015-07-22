package net.dragberry.expman.business;

import javax.ejb.Remote;

import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;

@Remote
public interface InterchangeService {

	InterchangeType createInterchangeType(InterchangeType interchangeType);
	
	Interchange createInterchange(Interchange interchange);
}
