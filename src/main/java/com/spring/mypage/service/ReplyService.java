package com.spring.mypage.service;

import java.util.List;

import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ReplyDTO;

public interface ReplyService {
	
	List<ReplyDTO> list(Integer article_no) throws Exception;
	
	void addReply(ReplyDTO replyDTO) throws Exception;

	void removeReply(Integer reply_no) throws Exception;
	
	void update(ReplyDTO replyDTO) throws Exception;
	
	// 리플 페이징
	List<ReplyDTO> getRepliesPaging(Integer article_no, Section section) throws Exception;
	
	int countReplies(Integer article_no) throws Exception;

}
