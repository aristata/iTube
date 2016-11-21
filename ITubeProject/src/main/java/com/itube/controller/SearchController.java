package com.itube.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itube.domain.PageMaker;
import com.itube.domain.PostVO;
import com.itube.domain.SearchCriteria;
import com.itube.service.PostService;

@RequestMapping("/searchPosts")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	 
	@Inject
	private PostService service;
	
	@RequestMapping(value = "/searchPage" ,method = RequestMethod.GET)
	public void searchPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		System.out.println(cri.toString());
		
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/readSearch", method = RequestMethod.GET)
	public void readSearchPage(@RequestParam("pid") int pid, @ModelAttribute("Cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.readPost(pid));
	}
	@RequestMapping(value = "/removeSearch", method = RequestMethod.POST)
	 public String removeSearchPage(@RequestParam("pid") int pid, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		
		service.removePost(pid);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/posts/readSearch";
	 } 
	@RequestMapping(value="/modifySearch", method = RequestMethod.GET) 
	public void modifyPagingGET(int pid, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.readPost(pid));
	}
	@RequestMapping(value = "modifySearch", method = RequestMethod.POST)
	public String modifyPagingPOST(PostVO postvo,SearchCriteria cri,  RedirectAttributes rttr) throws Exception{
		
		logger.info(cri.toString());
		service.modifyPost(postvo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/posts/readSearch";
	}
	
}
