package com.itube.controller;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itube.domain.UserVO;
import com.itube.persistence.UserDAOImpl;
import com.itube.service.UserServiceImpl;


@Controller
@RequestMapping("/join")
public class JoinController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserDAOImpl userDAO;
	
	@RequestMapping("/")
	public String join(){
			
		return "join/join";
	}
	
	@RequestMapping("/execute")
	public String execute(@ModelAttribute("id") String id,@ModelAttribute("pwd") String pwd,@ModelAttribute("nickname") String nickname,@ModelAttribute("email") String email){

		UserVO uservo = new UserVO();
		uservo.setUid(UUID.randomUUID().toString());
		uservo.setId(id);
		uservo.setPwd(pwd);
		uservo.setNickname(nickname);
		uservo.setEmail(email);
		uservo.setGrade(0);
		uservo.setEmailConfirm(false);
		
		userService.join(uservo);
		
		return "join/join";
	}
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public ResponseEntity<String> idCheck(@ModelAttribute("id") String id){
		ResponseEntity<String> entity = null;
		try {
			UserVO check = userDAO.selectID(id);
			if(check == null){
				entity = new ResponseEntity<String>("not", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("exist", HttpStatus.OK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
	public ResponseEntity<String> nicknameCheck(@ModelAttribute("nickname") String nickname){
		ResponseEntity<String> entity = null;
		try {
			UserVO check = userDAO.selectNickname(nickname);
			if(check == null){
				entity = new ResponseEntity<String>("not", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("exist", HttpStatus.OK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
	public ResponseEntity<String> emailCheck(@ModelAttribute("email") String email){
		ResponseEntity<String> entity = null;
		try {
			UserVO check = userDAO.selectEmail(email);
			if(check == null){
				entity = new ResponseEntity<String>("not", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("exist", HttpStatus.OK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}

