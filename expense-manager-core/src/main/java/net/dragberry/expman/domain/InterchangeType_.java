package net.dragberry.expman.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(InterchangeType.class)
public class InterchangeType_ {

	public static volatile SingularAttribute<InterchangeType, Long> interchangeTypeKey;
	public static volatile SingularAttribute<InterchangeType, String> name;
	public static volatile SingularAttribute<InterchangeType, String> type;
	public static volatile SingularAttribute<InterchangeType, Customer> customer;
}
