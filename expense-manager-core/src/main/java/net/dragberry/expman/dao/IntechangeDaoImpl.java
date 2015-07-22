package net.dragberry.expman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.domain.Interchange_;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.result.ResultList;

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

	@Override
	public ResultList<Interchange> fetchInterchangeList(InterchangeListQuery interchangeListQuery) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Interchange> cq = cb.createQuery(Interchange.class);
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Interchange> interchangeRoot = cq.from(Interchange.class);
		Root<Interchange> countRoot = cqCount.from(Interchange.class);
		
		if (interchangeListQuery != null) {
			Predicate where = getWhereClause(interchangeRoot, cb, interchangeListQuery);
			Predicate whereCount = getWhereClause(countRoot, cb, interchangeListQuery);
			addWhereClause(cq, where);
			addWhereClause(cqCount, whereCount);
		}
		
		cqCount.select(cb.count(countRoot));
		
		Map<Class<?>, From<?, ?>>  sortMap = new HashMap<Class<?>, From<?,?>>();
		sortMap.put(Interchange.class, interchangeRoot);
		cq.orderBy(getOrders(interchangeListQuery.getSortList(), sortMap, cb));
		
		TypedQuery<Interchange> query = getEntityManager().createQuery(cq);
        setPageableParams(interchangeListQuery, query);
        
        List<Interchange> list = query.getResultList();
        Long count = getEntityManager().createQuery(cqCount).getSingleResult();
		
        ResultList<Interchange> resultList = new ResultList<Interchange>();
		resultList.setList(list);
		resultList.setCount(count);
        resultList.setPageNumber(interchangeListQuery.getPageNumber());
        resultList.setPageSize(interchangeListQuery.getPageSize());
		return resultList;
	}
	
	/**
	 * Get 'WHERE' clause for interchange list query
	 * 
	 * @param root
	 * @param cb
	 * @param interchangeListQuery
	 * @return
	 */
	private Predicate getWhereClause(Root<Interchange> root, CriteriaBuilder cb, InterchangeListQuery interchangeListQuery) {
		Predicate where = null;
		where = addAndLikeExpression(interchangeListQuery.getDescription(), Interchange_.description, where, cb, root);
		where = addRangeExpression(interchangeListQuery.getFromAmount(), interchangeListQuery.getToAmount(), Interchange_.amount, where, cb, root);
		where = addRangeExpression(interchangeListQuery.getFromDate(), interchangeListQuery.getToDate(), Interchange_.processingDate, where, cb, root);
		where = addAndInExpression(root, cb, interchangeListQuery.getCurrencyList(), Interchange_.currency, where);
		where = addAndInExpression(root, cb, interchangeListQuery.getCounterPartyList(), Interchange_.counterParty, where);
		where = addAndInExpression(root, cb, interchangeListQuery.getInterchangeTypeList(), Interchange_.interchangeType, where);
		return where;
	}

}
