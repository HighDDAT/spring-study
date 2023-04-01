package com.spring.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mypage.commons.paging.PageMaker;
import com.spring.mypage.commons.paging.Section;
import com.spring.mypage.domain.ReplyDTO;
import com.spring.mypage.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	private final ReplyService replyService;
	
	@Inject
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	// 리플 등록
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyDTO replyDTO) {
		ResponseEntity<String> entity = null;
		
		try {
			replyService.addReply(replyDTO);
			entity = new ResponseEntity<String>("regSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	 
	// 리플 리스트
	@RequestMapping(value = "/all/{article_no}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>>list(@PathVariable("article_no")Integer article_no) {
		ResponseEntity<List<ReplyDTO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<ReplyDTO>>(replyService.list(article_no), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 리플 페이징 리스트
	@RequestMapping(value = "/{article_no}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPaging(@PathVariable("article_no")Integer article_no,
														@PathVariable("page")Integer page) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			
			Section section = new Section();
			section.setPage(page);
			
			List<ReplyDTO> replies = replyService.getRepliesPaging(article_no, section);
			int repliesCount = replyService.countReplies(article_no);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setSection(section);
			pageMaker.setTotalCount(repliesCount);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("replies", replies);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 리플 수정
	@RequestMapping(value = "/{reply_no}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("reply_no")Integer reply_no, @RequestBody ReplyDTO replyDTO) {
		ResponseEntity<String> entity = null;
		
		try {
			replyDTO.setReply_no(reply_no);
			replyService.update(replyDTO);
			entity = new ResponseEntity<String>("modSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 리플 삭제
	@RequestMapping(value = "/{reply_no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("reply_no")Integer reply_no) {
		ResponseEntity<String> entity = null;
		
		try {
			replyService.removeReply(reply_no);
			entity = new ResponseEntity<String>("delSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
