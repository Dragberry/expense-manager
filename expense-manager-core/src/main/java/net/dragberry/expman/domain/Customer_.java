package net.dragberry.expman.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Customer.class)
public class Customer_ {
	
	public static volatile SingularAttribute<Customer, Long> customerKey;
	public static volatile SingularAttribute<Customer, String> customerName;
	public static volatile SingularAttribute<Customer, Boolean> enabled;
	public static volatile SetAttribute<Customer, Role> roles;

}
