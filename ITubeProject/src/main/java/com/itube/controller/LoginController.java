package com.itube.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itube.domain.UserVO;
import com.itube.dto.LoginDTO;
import com.itube.service.UserServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginController {
			
	@Autowired
	private UserServiceImpl userService;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- Jae Yeong
	/*@RequestMapping("/")
	public String login(){
		
		return "login/login";
	}
	
	@RequestMapping("/execute")
	public String execute(@ModelAttribute("id") String id, @ModelAttribute("pwd") String pwd){
		boolean flag = userService.login(id, pwd);
		
		if(flag)
			return "main/main";
		else
			return "login/login";
	}*/
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- Jae Yeong
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- Seong Min
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(@ModelAttribute("dto") LoginDTO dto, Model model){
		System.out.println("컨트롤러 동작 확인 1 : 로그인 페이지가 열렸습니다.");
		return "login/login";
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
		System.out.println("컨트롤러 동작 확인 2 : 로그인포스트 메소드가 실행되었습니다.");
		UserVO vo = userService.login(dto);
		if(vo == null){
			return;
		}
		model.addAttribute("userVO", vo);
		
	}
	/* 실제로 로그인 처리가 이뤄지는 부분
	 * 사용자가 존재하는 경우 userVO 라는 이름으로 Model 객체에 저장됨
	 * HttpSession과 관련된 작업은 인터셉터에서 처리함
	 * */
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- Seong Min
	
	
}
