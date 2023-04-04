package com.spring.mypage.article.persistence;

import java.util.List;

import com.spring.mypage.article.domain.ArticleDTO;
import com.spring.mypage.commons.paging.SearchSection;
import com.spring.mypage.commons.paging.Section;

public interface ArticleDAO {

	// CRUD 게시판 구현 추상메서드
	void create(ArticleDTO articleDTO) throws Exception;

	ArticleDTO read(Integer article_no) throws Exception;

    void update(ArticleDTO articleDTO) throws Exception;

    void delete(Integer article_no) throws Exception;

    List<ArticleDTO> listAll() throws Exception;
    
    // 페이징 처리
    List<ArticleDTO> listPaging(int page) throws Exception;
    
    // 페이지 계산 처리
    List<ArticleDTO> listSection(Section section) throws Exception;
    
    // 전체 게시글 수 구하기
    int countArticles(Section section) throws Exception;
    
    // 검색 처리를 위한 영속성 계층 구현
    List<ArticleDTO> listSearch(SearchSection searchSection) throws Exception;
    
    // 검색 결과와 검색 결과의 수를 반환
    int countSearchedArticles(SearchSection searchSection) throws Exception;
    
    // 리플 갯수 갱신 관련
    void updateReplyCnt(Integer article_no, int amount) throws Exception;
    
    // 조회수
    void updateViewCnt(Integer article_no) throws Exception;
    
}
