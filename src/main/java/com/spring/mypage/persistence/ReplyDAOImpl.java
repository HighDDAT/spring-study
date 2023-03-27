package com.spring.mypage.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.spring.mypage.domain.ReplyDTO;

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

}
