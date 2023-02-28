package com.spring.mypage.persistence;

import java.util.List;

import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ArticleDTO;

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
}
