package com.spring.mypage.commons.paging;

public class SearchSection extends Section {
	
	// 검색 조건
	private String searchType;
	// 검색 키워드
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchSection [searchType=" + searchType + ", keyword = " + keyword + "]";
	}
	
	
	
	

}
