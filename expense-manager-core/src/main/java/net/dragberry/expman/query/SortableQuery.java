package net.dragberry.expman.query;

import java.util.Set;
import java.util.TreeSet;

import net.dragberry.expman.query.sort.SortItem;
import net.dragberry.expman.query.sort.SortOrder;

public class SortableQuery extends PageableQuery {
	
	private static final long serialVersionUID = 912540237403603297L;
	
	private Set<SortItem> sortList = new TreeSet<SortItem>();
	
	public void addSortItem(String field, SortOrder sortOrder, Class<?> type, Integer order) {
        SortItem sortItem = new SortItem();
        sortItem.setDirection(sortOrder);
        sortItem.setField(field);
        sortItem.setOrder(order);
        sortItem.setType(type);
        if (sortList == null) {
        	sortList = new TreeSet<SortItem>();
        }
       sortList.add(sortItem);
    }

	public Set<SortItem> getSortList() {
        return sortList;
    }
}
