package com.itube.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itube.interceptor.LoginInterceptor;
import com.itube.service.AdminServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	@Inject
	private AdminServiceImpl service;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info(locale.getCountry()+" "+locale.getVariant()+" "+locale.getLanguage());
		
		
		return "main/main";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		if(session.getAttribute(LoginInterceptor.LOGIN) != null)
			session.removeAttribute(LoginInterceptor.LOGIN);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/hot", method = RequestMethod.POST)
	public String hot(Model model) throws Exception{
		model.addAttribute("postlist", service.showBestPost());
		return "main/main";
	}
}
