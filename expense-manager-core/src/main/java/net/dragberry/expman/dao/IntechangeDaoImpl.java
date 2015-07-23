package net.dragberry.expman.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dragberry.expman.domain.Customer_;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.domain.InterchangeType_;
import net.dragberry.expman.domain.Interchange_;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.query.InterchangeTypeListQuery;
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
	public ResultList<InterchangeType> fetchInterchangeTypeList(InterchangeTypeListQuery interchangeTypeListQuery) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<InterchangeType> cq = cb.createQuery(InterchangeType.class);
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<InterchangeType> interchangeRoot = cq.from(InterchangeType.class);
		Root<InterchangeType> countRoot = cqCount.from(InterchangeType.class);
		
		if (interchangeTypeListQuery != null) {
			Predicate where = getWhereClauseForInterchangeType(interchangeRoot, cb, interchangeTypeListQuery);
			Predicate whereCount = getWhereClauseForInterchangeType(countRoot, cb, interchangeTypeListQuery);
			addWhereClause(cq, where);
			addWhereClause(cqCount, whereCount);
		}
		
		cqCount.select(cb.count(countRoot));
		
		return executePageableListQuery(interchangeTypeListQuery, cqCount, cq);
	}
	
	/**
	 * Get 'WHERE' clause for interchangeType list query
	 * 
	 * @param root
	 * @param cb
	 * @param interchangeTypeListQuery
	 * @return
	 */
	private Predicate getWhereClauseForInterchangeType(Root<InterchangeType> root, CriteriaBuilder cb, InterchangeTypeListQuery interchangeTypeListQuery) {
		Predicate where = null;
		where = addAndLikeExpression(interchangeTypeListQuery.getName(), InterchangeType_.name, where, cb, root);
		where = addAndEqualsExpression(interchangeTypeListQuery.getType(), InterchangeType_.type, where, cb, root);
		where = addAndEqualsExpression(interchangeTypeListQuery.getCustomer(), InterchangeType_.customer, where, cb, root);
		return where;
	}

	@Override
	public ResultList<Interchange> fetchInterchangeList(InterchangeListQuery interchangeListQuery) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Interchange> cq = cb.createQuery(Interchange.class);
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Interchange> interchangeRoot = cq.from(Interchange.class);
		Root<Interchange> countRoot = cqCount.from(Interchange.class);
		
		if (interchangeListQuery != null) {
			Predicate where = getWhereClauseForInterchange(interchangeRoot, cb, interchangeListQuery);
			Predicate whereCount = getWhereClauseForInterchange(countRoot, cb, interchangeListQuery);
			addWhereClause(cq, where);
			addWhereClause(cqCount, whereCount);
		}
		
		cqCount.select(cb.count(countRoot));
		
		Map<Class<?>, From<?, ?>>  sortMap = new HashMap<Class<?>, From<?,?>>();
		sortMap.put(Interchange.class, interchangeRoot);
		cq.orderBy(getOrders(interchangeListQuery.getSortList(), sortMap, cb));
		
        return executePageableListQuery(interchangeListQuery, cqCount, cq);
	}

	/**
	 * Get 'WHERE' clause for interchange list query
	 * 
	 * @param root
	 * @param cb
	 * @param interchangeListQuery
	 * @return
	 */
	private Predicate getWhereClauseForInterchange(Root<Interchange> root, CriteriaBuilder cb, InterchangeListQuery interchangeListQuery) {
		Predicate where = null;
		where = addAndLikeExpression(interchangeListQuery.getDescription(), Interchange_.description, where, cb, root);
		where = addRangeExpression(interchangeListQuery.getFromAmount(), interchangeListQuery.getToAmount(), Interchange_.amount, where, cb, root);
		where = addRangeExpression(interchangeListQuery.getFromDate(), interchangeListQuery.getToDate(), Interchange_.processingDate, where, cb, root);
		where = addAndInExpression(root, cb, interchangeListQuery.getCurrencyList(), Interchange_.currency, where);
		where = addAndInExpression(root, cb, interchangeListQuery.getCounterPartyList(), Interchange_.counterParty, where);
		where = addAndInExpression(root, cb, interchangeListQuery.getInterchangeTypeList(), Interchange_.interchangeType, where);
		return where;
	}

	@Override
	public BigDecimal getRealTimeBalance(Long customerKey) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT SUM(CASE WHEN IT.TYPE = 'C' THEN -I.AMOUNT ELSE I.AMOUNT END) AS BALANCE"
					+ " FROM INTERCHANGE I" 
					+ " JOIN INTERCHANGE_TYPE IT ON I.INTERCHANGE_TYPE_KEY = IT.INTERCHANGE_TYPE_KEY" 
					+ " WHERE I.CUSTOMER_KEY = :customerKey");
		query.setParameter(Customer_.customerKey.getName(), customerKey);
		return (BigDecimal) query.getSingleResult();
	}

}
