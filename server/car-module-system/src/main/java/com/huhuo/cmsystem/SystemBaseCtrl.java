package com.huhuo.cmsystem;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.huhuo.carservicecore.constant.MemcachedRegion.RegionSys;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.constant.Constant;
import com.huhuo.cmsystem.memcached.IServCmsysMemcached;
import com.huhuo.cmsystem.security.Session;
import com.huhuo.cmsystem.security.Session.SessionKey;
import com.huhuo.cmsystem.security.user.IServUser;
import com.huhuo.webbase.general.HuhuoWebBaseBaseCtrl;

public class SystemBaseCtrl extends HuhuoWebBaseBaseCtrl  {

	@Resource(name = "cmsystemServCmsysMemcached")
	private IServCmsysMemcached iServCmsysMemcached;
	@Resource(name = "cmsystemServUser")
	private IServUser iServUser;
	
	/**
	 * make old session in memcached invalid, 
	 * and add a new session to memcached with new session id
	 * @param req an object supply session id
	 * @param user a user object store in DB with session id
	 * @return a user object with new session id store in memcached
	 */
	protected ModelUser setSession(HttpServletRequest req, ModelUser user) {
		// make old session invalid
		iServCmsysMemcached.delete(RegionSys.SECURITY_SESSION, user.getSessionId());
		logger.info("==> delete session from memcached --> sessionId={}", user.getSessionId());
		// add new session to memcached
		req.getSession().invalidate();
		String sessionId = req.getSession().getId();
		user.setSessionId(sessionId);
		Session session = new Session(SessionKey.USER, user);
		iServCmsysMemcached.set(RegionSys.SECURITY_SESSION, sessionId, session, 
				Constant.SECURITY_SESSION_EXPRIE_TIME, 60000);
		iServUser.update(user);
		return user;
	}
	/**
	 * get session from memcached with session id obtained with @param req
	 * @param req
	 * @return session object stored in memcached
	 */
	protected Session getSession(HttpServletRequest req) {
		return iServCmsysMemcached.get(RegionSys.SECURITY_SESSION, req.getSession().getId());
	}
	
	protected Boolean deleteSession(HttpServletRequest req) {
		// make old session invalid
		req.getSession().invalidate();
		String sessionId = req.getSession().getId();
		logger.info("==> delete session from memcached --> sessionId={}", sessionId);
		return iServCmsysMemcached.delete(RegionSys.SECURITY_SESSION, sessionId);
	}
	
}
