package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;

public class IntechangeDaoImpl extends AbstractDao implements InterchangeDao {

	@Override
	public Interchange createInterchange(Interchange interchange) {
		getEntityManager().persist(interchange);
		return interchange;
	}

	@Override
	public InterchangeType createInterchangeType(InterchangeType interchangeType) {
		getEntityManager().persist(interchangeType);
		return interchangeType;
	}

}
