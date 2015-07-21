package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;

public interface InterchangeDao {
	
	Interchange createInterchange(Interchange interchange);
	
	InterchangeType createInterchangeType(InterchangeType interchangeType);

}
