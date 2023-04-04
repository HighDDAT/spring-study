package com.spring.mypage.reply.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.reply.domain.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	private static String NAMESPACE = "com.spring.mypage.mappers.reply.ReplyMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public ReplyDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<ReplyDTO> list(Integer article_no) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".list", article_no);
	}

	@Override
	public void create(ReplyDTO replyDTO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", replyDTO);
	}

	@Override
	public void update(ReplyDTO replyDTO) throws Exception {
		sqlSession.update(NAMESPACE + ".update", replyDTO);
	}

	@Override
	public void delete(Integer reply_no) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", reply_no);
	}

	// 댓글 페이징
	@Override
	public List<ReplyDTO> listPaging(Integer article_no, Section section) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("article_no", article_no);
		paramMap.put("section", section);
		
		return sqlSession.selectList(NAMESPACE + ".listPaging", paramMap);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countReplies", article_no);
	}

	// 리플 갯수 갱신
	@Override
	public int getArticleNo(Integer reply_no) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getArticleNo", reply_no);
	}
	
	

}
