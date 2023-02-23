package com.spring.mypage.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mypage.domain.ArticleDTO;
import com.spring.mypage.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	private final ArticleService articleService;
	
	@Inject
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// 등록 페이지 이동
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String writeGET() {
		
		logger.info("글 작성페이지 이동 GET");
		
		return "/article/write";
	}
	
	// 등록 처리
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePOST(ArticleDTO articleDTO, 
							RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("글 작성처리 POST");
		logger.info(articleDTO.toString());
		articleService.create(articleDTO);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:/article/list";
	}
	
	// 목록 페이지 이동
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(@RequestParam("article_no") int article_no,
						Model model) throws Exception {
		logger.info("목록 페이지 이동");
		model.addAttribute("articles", articleService.listAll());
		
		return "/article/list";
	}
}
