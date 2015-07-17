package net.dragberry.expman.query;

import java.io.Serializable;

public class PageableQuery implements Serializable {
    
	private static final long serialVersionUID = 5744838670176681632L;

	private int pageSize = -1;
    
    private int pageNumber = -1;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
    
}
