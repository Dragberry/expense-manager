package net.dragberry.expman.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import net.dragberry.expman.query.PageableQuery;
import net.dragberry.expman.query.sort.SortItem;
import net.dragberry.expman.result.ResultList;

public class AbstractDao {
	
	private static final String PERCENT_QUOTE = "%";

	@PersistenceContext(unitName = "expensemanager")
    private EntityManager entityManager;

	protected EntityManager getEntityManager() {
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
	protected String wrap(String str) {
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
	protected List<Order> getOrders(Set<SortItem> sortList, Map<Class<?>, From<?, ?>> sortMap, CriteriaBuilder cb) {
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
	
	/**
	 * Add predicate2 to predicate1 with 'AND' operator. Null-safe operation.
	 * 
	 * @param predicate1
	 * @param predicate2
	 * @param cb
	 * @return
	 */
	protected <T> Predicate andExpression(Predicate predicate1, Predicate predicate2, CriteriaBuilder cb) {
		if (predicate1 != null) {
			predicate1 = cb.and(predicate1, predicate2);
	    } else {
	    	predicate1 = predicate2;
	    }
		return predicate1;
	}
	
	/**
	 * Add range expression with 'AND' logical operator to WHERE clause. Using
	 * with BigDecimal values.
	 * 
	 * @param min
	 * @param max
	 * @param attribute
	 * @param where
	 * @param cb
	 * @param root
	 * @return
	 */
	public static <T> Predicate addRangeExpression(BigDecimal min, BigDecimal max, SingularAttribute<T, BigDecimal> attribute, Predicate where, CriteriaBuilder cb, Root<T> root) {
		if (min != null) {
			if (where != null) {
				where = cb.and(where, cb.ge(root.get(attribute), min));
			} else {
				where = cb.ge(root.get(attribute), min);
			}
		}
		if (max != null) {
			if (where != null) {
				where = cb.and(where, cb.le(root.get(attribute), max));
			} else {
				where = cb.le(root.get(attribute), max);
			}
		}
		return where;
	}
	
	/**
	 * Add range expression with 'AND' logical operator to WHERE clause. Using
	 * with Date values.
	 * 
	 * @param min
	 * @param max
	 * @param attribute
	 * @param where
	 * @param cb
	 * @param root
	 * @return
	 */
	public static <T> Predicate addRangeExpression(Date min, Date max, SingularAttribute<T, Date> attribute, Predicate where, CriteriaBuilder cb, Root<T> root) {
		if (min != null) {
			if (where != null) {
				where = cb.and(where, cb.greaterThanOrEqualTo(root.get(attribute), min));
			} else {
				where = cb.greaterThanOrEqualTo(root.get(attribute), min);
			}
		}
		if (max != null) {
			if (where != null) {
				where = cb.and(where, cb.lessThanOrEqualTo(root.get(attribute), max));
			} else {
				where = cb.lessThanOrEqualTo(root.get(attribute), max);
			}
		}
		return where;
	}
	
	/**
	 * Add predicate2 to predicate1 with 'AND' operator. Null-safe operation.
	 * 
	 * @param predicate1
	 * @param predicate2
	 * @param cb
	 * @return
	 */
	public static <T> Predicate addAndLikeExpression(Predicate predicate1, Predicate predicate2, CriteriaBuilder cb) {
		if (predicate1 != null) {
			predicate1 = cb.and(predicate1, predicate2);
	    } else {
	    	predicate1 = predicate2;
	    }
		return predicate1;
	}
	
	/**
	 * Add 'IN' expression with 'AND' operator. Null-safe operation
	 * 
	 * @param root
	 * @param cb
	 * @param collection
	 * @param attribute
	 * @param where
	 * @return
	 */
	protected <T, A> Predicate addAndInExpression(Root<T> root, CriteriaBuilder cb,
			Collection<A> collection, SingularAttribute<T, A> attribute, Predicate where) {
		if (CollectionUtils.isNotEmpty(collection)) {
            In<A> objectList = cb.in(root.get(attribute));
            for (A object : collection) {
            	objectList.value(object);
            }
            where = addAndLikeExpression(where, objectList, cb);
        }
		return where;
	}
	
	/**
	 * Execute list query. Return {@link ResultList} with pageable params
	 * 
	 * @param pageableQuery
	 * @param cqCount
	 * @param query
	 * @return
	 */
	protected <T extends Serializable> ResultList<T> executePageableListQuery(PageableQuery pageableQuery,
			CriteriaQuery<Long> cqCount, CriteriaQuery<T> cq) {
		TypedQuery<T> query = getEntityManager().createQuery(cq);
        setPageableParams(pageableQuery, query);
		List<T> list = query.getResultList();
        Long count = getEntityManager().createQuery(cqCount).getSingleResult();
        ResultList<T> resultList = new ResultList<T>();
		resultList.setList(list);
		resultList.setCount(count);
        resultList.setPageNumber(pageableQuery.getPageNumber());
        resultList.setPageSize(pageableQuery.getPageSize());
		return resultList;
	}
}
