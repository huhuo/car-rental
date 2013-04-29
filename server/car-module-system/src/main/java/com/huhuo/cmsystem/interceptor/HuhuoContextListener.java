package com.huhuo.cmsystem.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.huhuo.cmsystem.file.ServFileUpload;

@WebListener("cmsystemHuhuoContextListener")
public class HuhuoContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServFileUpload.setSce(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
