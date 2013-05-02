package com.huhuo.cmsystem.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import com.huhuo.cmsystem.constant.Constant;
/**
 * authority validation
 * @author root
 */
@WebFilter(urlPatterns = {"*"})
public class AuthorityFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String loginUrlPrefix = "/cmsystem/security/validation";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		logger.info("request is intercepted ---> {}", new UrlPathHelper().getServletPath(req));
		if (req.getServletPath().startsWith(loginUrlPrefix)
				|| req.getSession().getAttribute(Constant.SESSION_USER) != null
				|| isResourceFile(req.getServletPath())) { // 登录相关的url放行，和已登录放行
			chain.doFilter(req, resp);
		} else {
			// 未登录，重定向到登录页面
			String contextPath = req.getServletContext().getContextPath();
			resp.sendRedirect(contextPath + "/cmsystem/security/validation/login-page.do");
		}
	}
	
	/**
	 * validate statistic resources
	 * @param servletPath
	 * @return
	 */
	private boolean isResourceFile(String servletPath) {
		boolean ret = false;
		if(servletPath!=null && servletPath.startsWith("/res")) {
			ret = true;
		}
		return ret;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
