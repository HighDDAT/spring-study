package com.spring.mypage.article.controller;

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
import com.spring.mypage.commons.paging.SearchSection;

@Controller
@RequestMapping("/article/paging/search")
public class ArticlePagingSearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticlePagingSearchController.class);
	
	private final ArticleService articleService;
	
	@Inject
	public ArticlePagingSearchController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() {

        logger.info("search writeGET()");

        return "article/search/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(ArticleDTO articleDTO,
                            RedirectAttributes redirectAttributes) throws Exception {

        logger.info("search writePOST()");

        articleService.create(articleDTO);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");

        return "redirect:/article/paging/search/list";
    }
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("searchSection") SearchSection searchSection,
						Model model) throws Exception {
		logger.info("search list()");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setSection(searchSection);
//		pageMaker.setTotalCount(articleService.countArticles(searchSection));
		pageMaker.setTotalCount(articleService.countSearchedArticles(searchSection));
		
//		model.addAttribute("articles", articleService.listSection(searchSection));
		model.addAttribute("articles", articleService.listSearch(searchSection));
		model.addAttribute("pageMaker", pageMaker);
		
		return "article/search/list";
	}
	
	// 조회 페이지
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("article_no")int article_no,
				@ModelAttribute("searchSection")SearchSection searchSection,
				Model model) throws Exception {
		
		logger.info("search read()");
		model.addAttribute("article", articleService.read(article_no));
		
		return "article/search/read";
	}
	
	// 수정 페이지
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGET(@RequestParam("article_no")int article_no,
					@ModelAttribute("searchSection")SearchSection searchSection,
					Model model) throws Exception {
		
		logger.info("search modifyGET()");
		logger.info(searchSection.toString());
		model.addAttribute("article",articleService.read(article_no));
		
		return "article/search/modify";
	}
	
	// 수정 처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(ArticleDTO articleDTO,
							SearchSection searchSection,
							RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("search modifyPOST()");
		articleService.update(articleDTO);
		
		redirectAttributes.addAttribute("page", searchSection.getPage());
		redirectAttributes.addAttribute("perPageNum", searchSection.getPerPageNum());
		redirectAttributes.addAttribute("searchType", searchSection.getSearchType());
		redirectAttributes.addAttribute("keyword", searchSection.getKeyword());
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/paging/search/list";
	}

	// 삭제 처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("article_no")int article_no,
					SearchSection searchSection,
					RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("search remove()");
		
		articleService.delete(article_no);
		redirectAttributes.addAttribute("page", searchSection.getPage());
		redirectAttributes.addAttribute("perPageNum", searchSection.getPerPageNum());
		redirectAttributes.addAttribute("searchType", searchSection.getSearchType());
		redirectAttributes.addAttribute("keyword", searchSection.getKeyword());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/paging/search/list";
	}
	
}
