package com.spring.mypage.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyTestController {

	@RequestMapping("/test")
	public String replyAjaxTest() {
		
		return "/reply/reply_test";
	}
}
