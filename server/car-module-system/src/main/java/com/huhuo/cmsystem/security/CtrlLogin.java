package com.huhuo.cmsystem.security;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.cmsystem.constant.Constant;
import com.huhuo.integration.exception.CtrlException;

@Controller("cmsystemCtrlLogin")
@RequestMapping(value = "/cmsystem/security/login")
public class CtrlLogin extends SystemBaseCtrl {

	protected String basePath = "system-manage/";

	@Resource(name = "smCaptchaProducer")
	private Producer captchaProducer;

	@RequestMapping(value = "/")
	public String loginpage() {
		return basePath + "loginpage";
	}

	@RequestMapping(value = "/login.do")
	public void login(HttpServletRequest req, HttpServletResponse resp,
			HttpSession session, ModelUser user) {
		
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.SESSION_USER);
		session.removeAttribute(Constant.SESSION_MODULE);
		return loginpage();
	}

	@RequestMapping(value = "/captcha.do")
	public void get(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setDateHeader("Expires", 0);
			// Set standard HTTP/1.1 no-cache headers.
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");
			// return a jpeg
			response.setContentType("image/jpeg");
			// create the text for the image
			String capText = captchaProducer.createText();
			// store the text in the session
			request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,
					capText);
			// create the image with the text
			BufferedImage bi = captchaProducer.createImage(capText);
			// ServletOutputStream out = response.getOutputStream();
			// write the data out
			ImageIO.write(bi, "jpg", response.getOutputStream());
			try {
				response.getOutputStream().flush();
			} finally {
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new CtrlException(e);
		}
	}

}
