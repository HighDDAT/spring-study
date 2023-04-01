package com.spring.mypage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mypage.commons.paging.SearchSection;
import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ArticleDTO;
import com.spring.mypage.persistence.ArticleDAO;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleDAO articleDAO;
	
	@Inject
	public ArticleServiceImpl(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	
	// CRUD 게시판
	@Override
	public void create(ArticleDTO articleDTO) throws Exception {
		articleDAO.create(articleDTO);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ArticleDTO read(Integer article_no) throws Exception {
		articleDAO.updateViewCnt(article_no);
		return articleDAO.read(article_no);
	}
	
	@Override
	public void update(ArticleDTO articleDTO) throws Exception {
		articleDAO.update(articleDTO);
	}
	
	@Override
	public void delete(Integer article_no) throws Exception {
		articleDAO.delete(article_no);
	}
	
	@Override
	public List<ArticleDTO> listAll() throws Exception {
		return articleDAO.listAll();
	}

	// 페이징 관련
	@Override
	public List<ArticleDTO> listSection(Section section) throws Exception {
		return articleDAO.listSection(section);
	}
	
	@Override
	public int countArticles(Section section) throws Exception {
		return articleDAO.countArticles(section);
	}

	// 검색 관련
	@Override
	public List<ArticleDTO> listSearch(SearchSection searchSection) throws Exception {
		return articleDAO.listSearch(searchSection);
	}

	@Override
	public int countSearchedArticles(SearchSection searchSection) throws Exception {
		return articleDAO.countSearchedArticles(searchSection);
	}
	
	
}
