package com.huhuo.cmsystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huhuo.cmsystem.constant.Constant;

public class UrlInterceptor implements HandlerInterceptor {
	private String loginUrlPrefix = "/system/enter";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getServletPath().startsWith(loginUrlPrefix)
				|| request.getSession().getAttribute(Constant.SESSION_USER) != null) { // 登录相关的url放行，和已登录放行
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/"); // 未登录，重定向到登录页面
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
