package com.spring.mypage.service;

import java.util.List;

import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ArticleDTO;

public interface ArticleService {
	
	// CRUD 구현
	void create(ArticleDTO articleDTO) throws Exception;
	
	ArticleDTO read(Integer article_no) throws Exception;
	
	void update(ArticleDTO articleDTO) throws Exception;
	
	void delete(Integer article_no) throws Exception;
	
	List<ArticleDTO> listAll() throws Exception;

	// 페이징
	List<ArticleDTO> listSection(Section section) throws Exception;
	
	int countArticles (Section section) throws Exception;
}
