package com.spring.mypage.persistence;

import java.util.List;

import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ReplyDTO;


public interface ReplyDAO {

	List<ReplyDTO> list(Integer article_no) throws Exception;
	
	void create(ReplyDTO replyDTO) throws Exception;
	
	void update(ReplyDTO replyDTO) throws Exception;
	
	void delete(Integer reply_no) throws Exception;
	
	// 댓글 페이징
	List<ReplyDTO> listPaging(Integer article_no, Section section) throws Exception;
	
	int countReplies(Integer article_no) throws Exception;
	
}