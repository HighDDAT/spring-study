package com.spring.mypage.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.mypage.commons.paging.SearchSection;
import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ArticleDTO;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	// mybatis mapping
	private static final String NAMESPACE = "com.spring.mypage.mappers.article.ArticleMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public ArticleDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// CRUD 게시판 부분
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
	
	// 페이징 부분
	@Override
	public List<ArticleDTO> listPaging(int page) throws Exception {
		
		// 페이지는 음수가 되면 안됨
		if (page <= 0) {
			page = 1;
		}
		
		// 페이지 범위 잡아주기
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE + ".listPaging", page);
	}
	
	// 페이지 계산
	@Override
	public List<ArticleDTO> listSection(Section section) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listSection", section);
	}
	
	// 페이징 전체 게시글 수 구하기
	@Override
	public int countArticles(Section section) throws Exception {
		return sqlSession.selectOne(NAMESPACE +".countArticles", section);
	}

	// 검색 관련
	@Override
	public List<ArticleDTO> listSearch(SearchSection searchSection) throws Exception {
		return sqlSession.selectList(NAMESPACE+".listSearch",searchSection);
	}

	@Override
	public int countSearchedArticles(SearchSection searchSection) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countSearchedArticles", searchSection);
	}

	// 리플 갯수 갱신 관련
	@Override
	public void updateReplyCnt(Integer article_no, int amount) throws Exception {

	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("article_no", article_no);
	    paramMap.put("amount", amount);

	    sqlSession.update(NAMESPACE + ".updateReplyCnt",paramMap);
	}

	// 조회수
	@Override
	public void updateViewCnt(Integer article_no) throws Exception {
		sqlSession.update(NAMESPACE + ".updateViewCnt", article_no);
	}
	
	
}
