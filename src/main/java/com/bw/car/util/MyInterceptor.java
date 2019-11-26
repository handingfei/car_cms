package com.bw.car.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,Object  handler) throws ServletException, IOException {
		
		//拦截规则:1如果session有管理员 的session就放行,否则就拦截
		//getSession(false) false如果有request 没没有session反会null,true 如果
		//么有则创建session 默认true
		
		HttpSession session = request.getSession(false);
		if (null!=session) {
			//从session获取 admin 的对象,如果有则放行
			Object object = session.getAttribute("driver");
			if (null!=object) {
				return true;
			}
			
		}
		
		request.setAttribute("message", "请重新登录后再试");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
		return false;
	}
}
