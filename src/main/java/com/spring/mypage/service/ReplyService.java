package com.spring.mypage.service;

import java.util.List;

import com.spring.mypage.domain.ReplyDTO;

public interface ReplyService {
	
	List<ReplyDTO> list(Integer article_no) throws Exception;
	
	void create(ReplyDTO replyDTO) throws Exception;
	
	void update(ReplyDTO replyDTO) throws Exception;
	
	void delete(Integer reply_no) throws Exception;

}
