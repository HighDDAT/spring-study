package com.spring.mypage.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.mypage.domain.ArticleDTO;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private static final String NAMESPACE = "com.spring.mypage.mappers.article.ArticleMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public ArticleDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void create(ArticleDTO articleDTO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", articleDTO);
	}
	
	@Override
	public ArticleDTO read(Integer article_no) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", article_no);
	}
	
	@Override
	public void update(ArticleDTO articleDTO) throws Exception {
		sqlSession.update(NAMESPACE + ".update", articleDTO);
	}
	
	@Override
	public void delete(Integer article_no) throws Exception {
		sqlSession.delete(NAMESPACE +".delete", article_no);
	}
	
	@Override
	public List<ArticleDTO> listAll() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}
}
