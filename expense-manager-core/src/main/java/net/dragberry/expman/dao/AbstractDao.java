package net.dragberry.expman.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;

import net.dragberry.expman.query.PageableQuery;
import net.dragberry.expman.query.sort.SortItem;

public class AbstractDao {
	
	private static final String PERCENT_QUOTE = "%";

	@PersistenceContext(unitName = "expensemanager")
    private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Add like expression with 'AND' logical operator to WHERE clause.
	 * 
	 * @param field
	 * @param attribute
	 * @param where
	 * @param cb
	 * @param root
	 * @return
	 */
	protected <Z, T> Predicate addAndLikeExpression(String field, SingularAttribute<T, String> attribute,
			Predicate where, CriteriaBuilder cb, From<Z, T> root) {
		if (StringUtils.isNotBlank(field)) {
			if (where != null) {
				where = cb.and(where, cb.like(root.get(attribute), wrap(field)));
			} else {
				where = cb.like(root.get(attribute), wrap(field));
			}
		}
		return where;
	}
	
	/**
	 * Add equals expression with 'AND' logical operator to WHERE clause.
	 * 
	 * @param field
	 * @param attribute
	 * @param where
	 * @param cb
	 * @param root
	 * @return
	 */
	protected <Z, T, F> Predicate addAndEqualsExpression(F field, SingularAttribute<T, F> attribute,
			Predicate where, CriteriaBuilder cb, From<Z, T> root) {
		if (field != null) {
			if (where != null) {
				where = cb.and(where, cb.equal(root.get(attribute), field));
			} else {
				where = cb.equal(root.get(attribute), field);
			}
		}
		return where;
	}
	
	/**
	 * Wrap string in percent quotes: %string%
	 * 
	 * @param str
	 * @return
	 */
	public static String wrap(String str) {
		return new StringBuilder(PERCENT_QUOTE).append(str).append(PERCENT_QUOTE).toString();
	}
	
	/**
	 * Null-safe add WHERE clause to query.
	 * 
	 * @param cq
	 * @param where
	 */
	protected <T> void addWhereClause(CriteriaQuery<T> cq, Predicate where) {
		if (where != null) {
			cq.where(where);
		}
	}
	
	/**
	 * Get the list of orders based on set of the sortList
	 * 
	 * @param sortList
	 * @param cb
	 * @param sortMap
	 */
	protected static List<Order> getOrders(Set<SortItem> sortList, Map<Class<?>, From<?, ?>> sortMap, CriteriaBuilder cb) {
		List<Order> orders = new ArrayList<Order>();
		for (SortItem sortItem : sortList) {
			switch (sortItem.getDirection()) {
			case ASCENDING:
				orders.add(cb.asc(sortMap.get(sortItem.getType()).get(sortItem.getField())));
				break;
			case DESCENDING:
				orders.add(cb.desc(sortMap.get(sortItem.getType()).get(sortItem.getField())));
				break;
			}
		}
		return orders;
	}
	
	/**
	 * Set up pageable params to query
	 * 
	 * @param pageableQuery
	 * @param query
	 */
	protected <T> void setPageableParams(PageableQuery pageableQuery, TypedQuery<T> query) {
		if (pageableQuery.getPageNumber() > -1 && pageableQuery.getPageSize() > 0) {
	        query.setFirstResult((pageableQuery.getPageNumber() - 1) * pageableQuery.getPageSize());
	        query.setMaxResults(pageableQuery.getPageSize());
		}
	}
	
}
