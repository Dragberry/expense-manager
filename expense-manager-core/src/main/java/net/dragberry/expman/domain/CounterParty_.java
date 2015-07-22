package net.dragberry.expman.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CounterParty.class)
public class CounterParty_ {

	public static volatile SingularAttribute<CounterParty, Long> counterPartyKey;
	public static volatile SingularAttribute<CounterParty, String> name;
}
