package net.dragberry.expman.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {

	public static volatile SingularAttribute<Role, Long> roleKey;
	public static volatile SingularAttribute<Role, String> roleName;
	
}
