package net.dragberry.expman.query.sort;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

public interface SortableQuery<T extends Serializable> {
	
    public List<SortItem> getSortList();
    
    public void addSortItem(SingularAttribute<T, String> attribute, SortOrder sortOrder);

}
