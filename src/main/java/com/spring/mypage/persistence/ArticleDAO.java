package com.spring.mypage.persistence;

import java.util.List;

import com.spring.mypage.domain.ArticleDTO;

public interface ArticleDAO {

	void create(ArticleDTO articleDTO) throws Exception;

	ArticleDTO read(Integer article_no) throws Exception;

    void update(ArticleDTO articleDTO) throws Exception;

    void delete(Integer article_no) throws Exception;

    List<ArticleDTO> listAll() throws Exception;
}
