package com.spring.mypage.article.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mypage.article.domain.ArticleDTO;
import com.spring.mypage.article.service.ArticleService;
import com.spring.mypage.commons.paging.PageMaker;
import com.spring.mypage.commons.paging.Section;

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {

	private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);
	
	private final ArticleService articleService;
	
	@Inject
	public ArticlePagingController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// 등록 페이지 이동
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String writeGET() {
		
		logger.info("paging writeGET()");
		
		return "/article/paging/write";
	}
	
	// 등록 처리
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePOST(ArticleDTO articleDTO, 
							RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("paging writePOST()");
		logger.info(articleDTO.toString());
		
		articleService.create(articleDTO);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:/article/paging/list";
	}
	
	// 목록 페이지 이동
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, Section section) throws Exception {
		
		logger.info("paging list()");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setSection(section);
		pageMaker.setTotalCount(articleService.countArticles(section));
		
		model.addAttribute("articles", articleService.listSection(section));
		model.addAttribute("pageMaker", pageMaker);
		
		return "/article/paging/list";
	}
	
	// 조회 페이지 이동
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(@RequestParam("article_no") int article_no,
					@ModelAttribute("section") Section section,
						Model model) throws Exception {
		logger.info("paging read()");
		model.addAttribute("article", articleService.read(article_no));
		
		return "/article/paging/read";
	}
	
	// 수정 페이지 이동
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyGET(@RequestParam("article_no") int article_no,
					@ModelAttribute("section") Section section,
							Model model) throws Exception {
		
		logger.info("paging modifyGET()");
		model.addAttribute("article", articleService.read(article_no));
		
		return "/article/paging/modify";
	}
	
	// 수정 처리
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(ArticleDTO articleDTO,
							Section section,
							RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("paging modifyPOST()");
		
		articleService.update(articleDTO);
		redirectAttributes.addAttribute("page", section.getPage());
		redirectAttributes.addAttribute("perPageNum", section.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/paging/list";
	}
	
	// 삭제 처리
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("article_no") int article_no,
						Section section,
						RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("paging remove()");
		
		articleService.delete(article_no);
		
		redirectAttributes.addAttribute("page", section.getPage());
		redirectAttributes.addAttribute("perPageNum", section.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/paging/list";
	}
	
}
