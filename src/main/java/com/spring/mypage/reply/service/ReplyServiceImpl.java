package com.spring.mypage.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mypage.article.persistence.ArticleDAO;
import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.reply.domain.ReplyDTO;
import com.spring.mypage.reply.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	private final ReplyDAO replyDAO;
	private final ArticleDAO articleDAO;
	
	@Inject
	public ReplyServiceImpl(ReplyDAO replyDAO, ArticleDAO articleDAO) {
		this.replyDAO = replyDAO;
		this.articleDAO = articleDAO;
	}
	
	@Override
	public List<ReplyDTO> list(Integer article_no) throws Exception {
		return replyDAO.list(article_no);
	}
	
	// 리플 등록 트랜잭션 처리
	@Transactional
	@Override
	public void addReply(ReplyDTO replyDTO) throws Exception {
		replyDAO.create(replyDTO); // 리플 등록
		articleDAO.updateReplyCnt(replyDTO.getArticle_no(), 1); // 리플 갯수 +1
	}

	// 리플 삭제 트랜잭션 처리
	@Transactional
	@Override
	public void removeReply(Integer reply_no) throws Exception {
		int article_no = replyDAO.getArticleNo(reply_no); // 해당 리플의 게시물 번호 조회
		replyDAO.delete(reply_no); // 리플 삭제
		articleDAO.updateReplyCnt(article_no, -1);
	}
	
	@Override
	public void update(ReplyDTO replyDTO) throws Exception {
		replyDAO.update(replyDTO);
	}
	
	// 리플 페이징
	@Override
	public List<ReplyDTO> getRepliesPaging(Integer article_no, Section section) throws Exception {
		return replyDAO.listPaging(article_no, section);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
		return replyDAO.countReplies(article_no);
	}

}
