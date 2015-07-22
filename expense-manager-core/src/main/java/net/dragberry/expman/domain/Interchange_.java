package net.dragberry.expman.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Interchange.class)
public class Interchange_ {

	public static volatile SingularAttribute<Interchange, Long> interchangeKey;
	public static volatile SingularAttribute<Interchange, String> description;
	public static volatile SingularAttribute<Interchange, BigDecimal> amount;
	public static volatile SingularAttribute<Interchange, Date> processingDate;
	public static volatile SingularAttribute<Interchange, InterchangeType> interchangeType;
	public static volatile SingularAttribute<Interchange, CounterParty> counterParty;
	public static volatile SingularAttribute<Interchange, String> currency;
	public static volatile SingularAttribute<Interchange, Customer> customer;
}
