package net.dragberry.expman.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import net.dragberry.expman.domain.Role;
import net.dragberry.expman.domain.Role_;

public class RoleDaoImp extends AbstractDao implements RoleDao {

	@Override
	public Role findRoleByName(String roleName) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(Role.class);
		Root<Role> roleRoot = cq.from(Role.class);
		Predicate where = null;
		if (StringUtils.isNotBlank(roleName)) {
			where = addAndEqualsExpression(roleName, Role_.roleName, where, cb, roleRoot);
		}
		addWhereClause(cq, where);
		return getEntityManager().createQuery(cq).getSingleResult();
	}

}
