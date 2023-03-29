package com.spring.mypage.persistence;

import java.util.Iterator;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mypage.domain.ReplyDTO;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyDAOTest {

	@Inject
	private ReplyDAO replyDAO;
	
	@Test
	public void testReplyCreate() throws Exception {
		for (int i = 1; i <= 100; i++) {
			ReplyDTO replyDTO = new ReplyDTO();
			replyDTO.setArticle_no(3);
			replyDTO.setReply_text(i + "번째 리플 입니다.");
			replyDTO.setReply_writer("tester" +(i%10));
			replyDAO.create(replyDTO);
			
		}
	}
	
	@Test
	public void testReplyList() throws Exception {
		log.info(replyDAO.list(3).toString());
	}
	
	@Test
	public void testReplyUpdate() throws Exception {
		
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setArticle_no(3);
		replyDTO.setReply_text(3+"번째 리플 수정");
		replyDAO.update(replyDTO);
	}
	
	@Test
	public void testReplyDelete() throws Exception {
		replyDAO.delete(3);
	}
}
