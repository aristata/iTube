package com.itube.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itube.domain.Criteria;
import com.itube.domain.PageMaker;
import com.itube.service.AdminServiceImpl;
import com.itube.service.NoticeServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Inject
	private AdminServiceImpl service;
	
	@Inject
	private NoticeServiceImpl nService;
		
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public String admin(){
		return "admin/admin";
	}
	
	@RequestMapping(value = "/userManagement", method = RequestMethod.POST)
	public String userManagement(Model model) throws Exception {
		
		model.addAttribute("userManagement", service.showUser());
		System.out.println(model);	
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "postManagement", method = RequestMethod.POST)
	public String postManagement(Model model) throws Exception{
		model.addAttribute("postManagement", service.showPost());
		return "admin/admin";
	}
	
	@RequestMapping(value = "userGradeModify", method = RequestMethod.POST)
	public String userGradeModify(@RequestParam("userID") String userID, @RequestParam("userGrade") int userGrade) throws Exception{
		System.out.println("userGrade = "+userGrade);
		service.userModify(userID, userGrade);
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "userDelete", method = RequestMethod.POST)
	public String userDelete(@RequestParam("userID") String userID) throws Exception{
		
		service.userDelete(userID);
		
		return "admin/admin";
	}
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String notice(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		System.out.println(cri.toString());

		model.addAttribute("list", nService.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(nService.listCountCriteria(cri));

		model.addAttribute("pageMaker", pageMaker);
		
		return "posts/noticeList";
	}
	
	@RequestMapping(value = "postCategoryModify", method = RequestMethod.POST)
	public String postCategoryModify(@RequestParam("postID") Integer postID, @RequestParam("postCategory") String postCategory) throws Exception {
		System.out.println("---------------------------------------");
		System.out.println("AdminController - postCategoryModifyTest");
		System.out.println("postID : " + postID);
		System.out.println("postCategory : " + postCategory);
		System.out.println("---------------------------------------");
		service.postModify(postID, postCategory);
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "postDelete", method = RequestMethod.POST)
	public String postDelete(@RequestParam("postID") Integer postID) throws Exception{
		
		service.postDelete(postID);
		
		return "admin/admin";
	}
	
}
