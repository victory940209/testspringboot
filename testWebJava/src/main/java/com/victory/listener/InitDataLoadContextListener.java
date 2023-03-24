package com.victory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitDataLoadContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("arg"+ arg0);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}

}
