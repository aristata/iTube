package com.itube.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itube.domain.UserVO;
import com.itube.service.PostServiceImpl;
import com.itube.service.UserServiceImpl;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Inject
	private UserServiceImpl service;
	@Inject
	private PostServiceImpl pService;
	
	@RequestMapping("/")
	public String mypage(){
		return "mypage/mypage";
	}
	
	//회원정보관리를 눌렀을때 회원정보관리 페이지가 표시되도록 한다.
	@RequestMapping(value = "/adjust", method = {RequestMethod.GET})
	public String adjust(){
		return "mypage/mypagemember";
	}
	
	//동영상 업로드를 눌렀을때 동영상 업로드 페이지가 표시되도록 한다.
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(){
		return "upload/upload";
	}
	
	//회원정보를 수정하고 수정버튼을 눌렀을때 DB를 업데이트한다.
	@RequestMapping(value = "/memberModify", method = RequestMethod.POST)
	public String memberModify(HttpServletRequest request){
		UserVO userVO = new UserVO();
		userVO.setUid(request.getParameter("uid"));
		userVO.setId(request.getParameter("member_id"));
		userVO.setNickname(request.getParameter("member_nickname"));
		userVO.setPwd(request.getParameter("member_password"));
		userVO.setEmail(request.getParameter("member_email"));
		userVO.setGrade(Integer.parseInt(request.getParameter("grade")));
		System.out.println("-----------------------------------------");
		System.out.println("MyPageController 에서 parameter 확인 작업");
		System.out.println(userVO.toString());
		System.out.println("-----------------------------------------");
		service.adjust(userVO);
		return "mypage/mypage";
	}
	@RequestMapping(value = "bookmark", method = {RequestMethod.GET, RequestMethod.POST})
	public String bookmark(@RequestParam("uid") Integer uid, Model model) throws Exception{
	
		
		model.addAttribute("list", pService.listBookmark(uid));
		
		return "mypage/bookmark";
	}
}
