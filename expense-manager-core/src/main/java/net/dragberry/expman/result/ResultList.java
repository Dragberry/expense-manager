package net.dragberry.expman.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class ResultList<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -419723315520709203L;
	
	private List<T> list = new ArrayList<>();
	
	private int page = 0;
	
	private int pageSize = Integer.MAX_VALUE;
	
	private long count = 0;
	
	public boolean isEmpty() {
		return CollectionUtils.isEmpty(list) || count == 0;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
