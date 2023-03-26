package com.spring.mypage.commons.paging;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	// 페이지 번호 개수
	private int displayPageNum = 10;
	
	private Section section;
	
	public void setSection(Section section) {
		this.section = section;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	// 게시글의 전체 개수가 정해지면 메서드 호출해서 계산
	private void calcData() {
		endPage = (int) (Math.ceil(section.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double) section.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * section.getPerPageNum() >= totalCount ? false : true;
	}
	
	// UriComponentsBuilder
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", section.getPerPageNum())
				.build();
		
		return uriComponents.toUriString();
	}
	
	// 검색 게시글의 목록 정보를 유지
	public String makeSearch(int page) {
		// 각 인자의 정보를 가지고 GET방식으로 전송
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", section.getPerPageNum())
				.queryParam("searchType", ((SearchSection)section).getSearchType())
				.queryParam("keyword", encoding(((SearchSection)section).getKeyword()))
				.build();
		
		return uriComponents.toUriString();
				
	}
	
	private String encoding(String keyword) {
	    if (keyword == null || keyword.trim().length() == 0) {
	        return "";
	    }

	    try {
	        return URLEncoder.encode(keyword, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        return "";
	    }
		
	}
	
}
