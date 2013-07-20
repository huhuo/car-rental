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

import com.huhuo.carservicecore.constant.MemcachedRegion.RegionSys;
import com.huhuo.carservicecore.db.AppContextFactory;
import com.huhuo.cmsystem.memcached.IServCmsysMemcached;
import com.huhuo.cmsystem.security.Session;
import com.huhuo.cmsystem.security.Session.SessionKey;
/**
 * authority validation
 * @author root
 */
@WebFilter(urlPatterns = {"*"})
public class AuthorityFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String loginUrlPrefix = "/cmsystem/security/validation";
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	private IServCmsysMemcached iServCmsysMemcached;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		iServCmsysMemcached = AppContextFactory.getContext().getBean(IServCmsysMemcached.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// object casting
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		logger.info("request is intercepted ---> {}", urlPathHelper.getServletPath(req));
		// get session from memcached
		Session session = iServCmsysMemcached.get(RegionSys.SECURITY_SESSION, req.getSession().getId());
		if (req.getServletPath().startsWith(loginUrlPrefix)
				|| (session != null && session.get(SessionKey.USER) != null)
				|| isResourceFile(req.getServletPath())) { // 登录相关的url放行，和已登录放行
			chain.doFilter(req, resp);
		} else {
			// 未登录，重定向到登录页面
//			String contextPath = req.getServletContext().getContextPath();
//			resp.sendRedirect(contextPath + "/cmsystem/security/validation/login-page.do");
			req.getRequestDispatcher("view/car-module-system/loginpage.jsp").forward(req, resp);
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
