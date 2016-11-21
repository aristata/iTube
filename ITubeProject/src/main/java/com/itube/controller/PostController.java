package com.itube.controller;

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

import com.itube.domain.Criteria;
import com.itube.domain.PageMaker;
import com.itube.domain.PostVO;
import com.itube.domain.SearchCriteria;
import com.itube.service.NoticeServiceImpl;
import com.itube.service.PostServiceImpl;

@Controller
@RequestMapping("/posts")
public class PostController {
	 private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	 
	@Inject
	private PostServiceImpl service;
	
	@Inject
	private NoticeServiceImpl nservice;

	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception {

		logger.info("show list Page with Criteria......................");

		model.addAttribute("list", service.listCriteria(cri));
	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {

		System.out.println(cri.toString());
		logger.info(cri.toString());

		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.listCountCriteria(cri));

		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/listPage2", method = RequestMethod.GET)
	public void listPage2(@ModelAttribute("cri") Criteria cri,@RequestParam("category") String category, Model model) throws Exception {

		System.out.println(cri.toString());
		logger.info(cri.toString());
		
		cri.setCategory(category);
		model.addAttribute("list", service.listCriteria2(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.listCountCriteria2(cri));

		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("pid") int pid, @ModelAttribute("Cri") Criteria cri, Model model) throws Exception{
		model.addAttribute(service.readPost(pid));
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("pid") int pid, @ModelAttribute("cri") Criteria cri, Model model)
			throws Exception {

		model.addAttribute(service.readPost(pid));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(PostVO postvo, Criteria cri, RedirectAttributes rttr) throws Exception {

		service.modifyPost(postvo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		
		return "redirect:/posts/listPage";
	}
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("pid") int pid, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		service.removePost(pid);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		
		return "redirect:/posts/listPage";
	}
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public void noticeList(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {

		System.out.println(cri.toString());

		System.out.println("postController NoticeList 호출 성공");
		model.addAttribute("list", nservice.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(nservice.listCountCriteria(cri));
		System.out.println(nservice.listCountCriteria(cri));
		
		
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/searchPage" , method = {RequestMethod.POST, RequestMethod.GET})
	public void searchPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		System.out.println(cri.toString());
		
		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
}