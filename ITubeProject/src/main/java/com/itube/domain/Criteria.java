package com.itube.domain;

public class Criteria {

	private int page;	
	private int perPageNum;
	private int pageStart;
	private String category;
	
	public Criteria(){
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page){
		
		if(page <= 0){
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum){
		
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}	
	
	public int getPageStart() {
		pageStart = (this.page -1)* perPageNum;
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPerPageNum(){
		
		return this.perPageNum;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", pageStart=" + pageStart + ", category="
				+ category + "]";
	}

}


