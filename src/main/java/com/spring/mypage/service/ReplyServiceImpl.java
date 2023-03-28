package com.spring.mypage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ReplyDTO;
import com.spring.mypage.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	private final ReplyDAO replyDAO;
	
	@Inject
	public ReplyServiceImpl(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	@Override
	public List<ReplyDTO> list(Integer article_no) throws Exception {
		return replyDAO.list(article_no);
	}

	@Override
	public void create(ReplyDTO replyDTO) throws Exception {
		replyDAO.create(replyDTO);
	}

	@Override
	public void update(ReplyDTO replyDTO) throws Exception {
		replyDAO.update(replyDTO);
	}

	@Override
	public void delete(Integer reply_no) throws Exception {
		replyDAO.delete(reply_no);
	}
	
	// 댓글 페이징
	@Override
	public List<ReplyDTO> getRepliesPaging(Integer article_no, Section section) throws Exception {
		return replyDAO.listPaging(article_no, section);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
		return replyDAO.countReplies(article_no);
	}
	
	

}
