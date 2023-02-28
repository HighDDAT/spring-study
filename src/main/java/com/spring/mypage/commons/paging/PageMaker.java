package com.spring.mypage.commons.paging;

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
	
	
}
