package com.huhuo.cmsystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

public class UrlInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	private String loginUrlPrefix = "/cmsystem/security";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("request is intercepted ---> {}", new UrlPathHelper().getServletPath(request));
		
//		if (request.getServletPath().startsWith(loginUrlPrefix)
//				|| request.getSession().getAttribute(Constant.SESSION_USER) != null) { // 登录相关的url放行，和已登录放行
//			return true;
//		}
//
//		response.sendRedirect(request.getContextPath() + "/"); // 未登录，重定向到登录页面
		return true;
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
