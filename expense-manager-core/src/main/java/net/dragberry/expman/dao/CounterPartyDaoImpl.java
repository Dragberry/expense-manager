package net.dragberry.expman.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.CounterParty_;
import net.dragberry.expman.query.CounterPartyListQuery;
import net.dragberry.expman.result.ResultList;

public class CounterPartyDaoImpl extends AbstractDao implements CounterPartyDao {

	@Override
	public CounterParty createCounterParty(CounterParty counterParty) {
		getEntityManager().persist(counterParty);
		return counterParty;
	}

	@Override
	public ResultList<CounterParty> fetchCounterPartyList(CounterPartyListQuery counterPartyListQuery) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<CounterParty> cq = cb.createQuery(CounterParty.class);
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<CounterParty> counterPartyRoot = cq.from(CounterParty.class);
		Root<CounterParty> countRoot = cqCount.from(CounterParty.class);
		
		if (counterPartyListQuery != null) {
			Predicate where = getWhereClause(counterPartyRoot, cb, counterPartyListQuery);
			Predicate whereCount = getWhereClause(countRoot, cb, counterPartyListQuery);
			addWhereClause(cq, where);
			addWhereClause(cqCount, whereCount);
		}
		
		cqCount.select(cb.count(countRoot));
		
		return executePageableListQuery(counterPartyListQuery, cqCount, cq);
	}
	
	/**
	 * Get 'WHERE' clause for counter party list query
	 * 
	 * @param root
	 * @param cb
	 * @param counterPartyListQuery
	 * @return
	 */
	private Predicate getWhereClause(Root<CounterParty> root, CriteriaBuilder cb, CounterPartyListQuery counterPartyListQuery) {
		Predicate where = null;
		where = addAndLikeExpression(counterPartyListQuery.getName(), CounterParty_.name, where, cb, root);
		return where;
	}

}
