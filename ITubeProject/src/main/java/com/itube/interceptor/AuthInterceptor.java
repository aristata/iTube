package com.itube.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* AuthInterceptor의 역할
 * 특정 경로에 접근하는 경우 현재 사용자가 로그인한 상태의 사용자인지를 체크한다.
 * \WEB-INF\spring\appServlet\servlet-context.xml 에 인터셉터할 특정 경로가 삽입되어있다.
 * */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private void saveDest(HttpServletRequest req) {

		String uri = req.getRequestURI();

		String query = req.getQueryString();

		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}

		if (req.getMethod().equals("GET")) {
			System.out.println("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		/*
		 * 만약 로그인이 되어 있지 않다면, 로그인 화면으로 이동하도록 한다.
		 * 로그인이 되어 있다면, 원래 이동하려고 했던 경로로 이동한다.
		 * */
		if (session.getAttribute("login") == null) {

			
			System.out.println("로그인이 필요합니다. 로그인 화면으로 이동합니다.");
			
			saveDest(request);

			response.sendRedirect("/login/login");
			return false;
		}
		return true;
	}

	
}
