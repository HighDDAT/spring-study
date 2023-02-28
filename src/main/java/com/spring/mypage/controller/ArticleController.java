package com.spring.mypage.controller;

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

import com.spring.mypage.commons.paging.PageMaker;
import com.spring.mypage.commons.paging.Section;
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
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		
		logger.info("목록 페이지 이동");
		model.addAttribute("articles", articleService.listAll());
		
		return "/article/list";
	}
	
	// 조회 페이지 이동
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(@RequestParam("article_no") int article_no,
						Model model) throws Exception {
		logger.info("조회 페이지 이동");
		model.addAttribute("article", articleService.read(article_no));
		
		return "/article/read";
	}
	
	// 수정 페이지 이동
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyGET(@RequestParam("article_no") int article_no,
							Model model) throws Exception {
		
		logger.info("수정 페이지 이동중 GET");
		model.addAttribute("article", articleService.read(article_no));
		
		return "/article/modify";
	}
	
	// 수정 처리
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(ArticleDTO articleDTO,
							RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("수정 처리 POST");
		articleService.update(articleDTO);
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/list";
	}
	
	// 삭제 처리
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("article_no") int article_no,
						RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("삭제 처리 POST");
		articleService.delete(article_no);
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/list";
	}
	
	// 페이징 처리
	@RequestMapping(value = "/listPaging", method = RequestMethod.GET)
	public String listPaging(Model model, Section section) throws Exception {
		
		logger.info("페이지 리스트");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setSection(section);
		
		// 페이지 개수 처리
		pageMaker.setTotalCount(articleService.countArticles(section));
		
		model.addAttribute("articles", articleService.listSection(section));
		model.addAttribute("pageMaker", pageMaker);
		
		return "/article/list_paging";
	}
	
	@RequestMapping(value = "/listSection", method = RequestMethod.GET)
	public String listSection(Model model, Section section) throws Exception {
		
		logger.info("페이징리스트 섹션");
		model.addAttribute("articles", articleService.listSection(section));
		return "/article/list_section";
	}
	
	// 목록페이지 정보유지 컨트롤러 구현
	@RequestMapping(value = "/readPaging", method = RequestMethod.GET)
	public String readPaging(@RequestParam("article_no") int article_no,
							 @ModelAttribute("section") Section section,
							 Model model) throws Exception {
		model.addAttribute("article", articleService.read(article_no));
		
		return "/article/read_paging";
	}
	
	@RequestMapping(value = "/modifyPaging", method = RequestMethod.GET)
	public String modifyGETPaging(@RequestParam("article_no") int article_no,
								  @ModelAttribute("section") Section section,
								  Model model) throws Exception {
		
		logger.info("modify get paging");
		model.addAttribute("article", articleService.read(article_no));
		
		return "/article/modify_paging";
	}
	
	@RequestMapping(value = "/modifyPaging", method = RequestMethod.POST)
	public String modifyPOSTPaging(ArticleDTO articleDTO,
								   Section section,
								   RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("modify post paging");
		articleService.update(articleDTO);
		redirectAttributes.addAttribute("page", section.getPage());
		redirectAttributes.addAttribute("perPageNum", section.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/listPaging";
	}
	
	@RequestMapping(value = "/removePaging", method = RequestMethod.POST)
	public String removePaging(@RequestParam("article_no") int article_no,
								Section section,
								RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("삭제 처리 Paging");
		articleService.delete(article_no);
		redirectAttributes.addAttribute("page", section.getPage());
		redirectAttributes.addAttribute("perPageNum", section.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/listPaging";
	}
}
