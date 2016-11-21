package com.itube.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* LoginController 에서 처리하지 않은 HttpSession과 관련된 작업을 처리하는 곳*/
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	public static final String LOGIN = "login";
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- start of postHandle
	/* 이 코드는 LoginController 가 동작한 이후, DispatcherServlet이 View를 처리하기 전에 동작합니다.
	 * 앞서 LoginController 에서 userVO라는 이름으로 객체를 담아둔 상태인데, 
	 * 이 상태를 체크해서 정보가 있으면, HttpSession에 저장하도록 만들었습니다.
	 * */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("LoginInterceptor의 postHandle이 실행되었습니다.");
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null){
			System.out.println("Login success !!!");
			session.setAttribute(LOGIN, userVO);
			System.out.println("현재 세션 : " + session.getAttribute(LOGIN));
		}else{
			System.out.println("실화냐?");
			response.sendError(500);
		}
		
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- end of postHandle
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- start of preHandel
	/* 이 코드는 LoginController 가 동작하기 이전에 인터셉트하여 동작합니다.
	 * 기존 HttpSession에 남아있는 정보가 있는 경우에 정보를 삭제합니다.
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("LoginInterceptor의 preHandle이 실행되었습니다.");
		
		// --------------------------------------------------------------------------------------------
		//Object handler 를 HandlerMethod 타입으로 캐스팅 한 다음 원래의 메소드와 객체를 확인하는 작업
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("연결된 객체 : " + method.getBean());
		System.out.println("연결된 메소드 : " + methodObj);
		//위 메시지가 콘솔화면에 출력된다면 preHandle이 정상적으로 작동한다고 할수있다.
		// --------------------------------------------------------------------------------------------
		
		HttpSession session = request.getSession();
		System.out.println("현재 세션 상태 : " + session.getAttribute(LOGIN));
		if(session.getAttribute(LOGIN) != null){
			System.out.println("removed session");
			session.removeAttribute(LOGIN);
		}
		return true;
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <-- end of preHandel
}
