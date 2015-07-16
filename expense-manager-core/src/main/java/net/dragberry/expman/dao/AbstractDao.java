package net.dragberry.expman.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;

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
	
}
