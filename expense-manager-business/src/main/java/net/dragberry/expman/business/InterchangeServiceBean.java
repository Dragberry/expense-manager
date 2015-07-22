package net.dragberry.expman.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.dragberry.expman.dao.InterchangeDao;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.result.ResultList;

@Stateless
public class InterchangeServiceBean implements InterchangeService {
	
	@Inject
	private InterchangeDao interchangeDao;

	@Override
	public InterchangeType createInterchangeType(InterchangeType interchangeType) {
		return interchangeDao.createInterchangeType(interchangeType);
	}

	@Override
	public Interchange createInterchange(Interchange interchange) {
		return interchangeDao.createInterchange(interchange);
	}

	@Override
	public ResultList<Interchange> fetchInterchangeList(InterchangeListQuery interchangeListQuery) {
		return interchangeDao.fetchInterchangeList(interchangeListQuery);
	}

}
